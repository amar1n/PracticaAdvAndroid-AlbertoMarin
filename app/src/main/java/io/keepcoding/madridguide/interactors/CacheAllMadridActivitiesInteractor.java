package io.keepcoding.madridguide.interactors;

import android.content.Context;
import android.content.SharedPreferences;

import org.joda.time.DateTime;

import io.keepcoding.madridguide.manager.db.MadridActivityDAO;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;
import io.keepcoding.madridguide.util.Constants;
import io.keepcoding.madridguide.util.MainThread;

public class CacheAllMadridActivitiesInteractor {

    public void execute(final Context context, final MadridActivities madridActivities, final CacheAllItemsInteractorResponse response) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MadridActivityDAO dao = new MadridActivityDAO(context);

                boolean bFlag = true;
                for (MadridActivity madridActivity: madridActivities.allItems()) {
                    bFlag = dao.insert(madridActivity) > 0;
                    if (!bFlag) {
                        break;
                    }
                }

                if (bFlag) {
                    final SharedPreferences preferences = context.getSharedPreferences(Constants.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(Constants.LAST_MADRIDACTIVITIES_DOWNLOAD_KEY, DateTime.now().toString());
                    editor.commit();
                }

                final boolean success = bFlag;
                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        if (response != null) {
                            response.response(success);
                        }
                    }
                });
            }
        }).start();
    }
}