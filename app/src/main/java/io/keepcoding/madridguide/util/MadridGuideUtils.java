package io.keepcoding.madridguide.util;

import android.content.Context;
import android.content.SharedPreferences;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class MadridGuideUtils {

    public boolean doDownload(final Context context, String donwloadKey) {
        boolean result = true;

        final SharedPreferences preferences = context.getSharedPreferences(Constants.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        final DateTime epoch = new DateTime(0, DateTimeZone.UTC);

        String sLastDownload = preferences.getString(donwloadKey, epoch.toString());
        if (sLastDownload != null) {
            DateTime now = DateTime.now();
            DateTime lastDownload = DateTime.parse(sLastDownload);
            result = now.isAfter(lastDownload.plusDays(7));
        }

        return result;
    }
}
