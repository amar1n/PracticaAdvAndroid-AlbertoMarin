package io.keepcoding.madridguide.util;

import android.content.Context;
import android.content.SharedPreferences;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class MadridGuideUtils {

    public static boolean doDownload(final Context context, String donwloadKey) {
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

    public static String getMapUrl(final float latitude, final float longitude) {
        return String.format("http://maps.googleapis.com/maps/api/staticmap?center=%s,%s&zoom=17&size=320x220&scale=2&markers=%%7Ccolor:0x9C7B14%%7C%s,%s",
                Float.toString(latitude), Float.toString(longitude), Float.toString(latitude), Float.toString(longitude));
    }
}
