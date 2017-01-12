package io.keepcoding.madridguide.interactors;

import android.content.Context;

import io.keepcoding.madridguide.manager.db.MadridActivityDAO;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;
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
