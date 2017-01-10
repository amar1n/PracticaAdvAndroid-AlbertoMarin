package io.keepcoding.madridguide.interactors;

import android.content.Context;
import android.os.Looper;

import io.keepcoding.madridguide.manager.db.MadridActivityDAO;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;

public class CacheAllMadridActivitiesInteractor {

    public void execute(final Context context, final MadridActivities madridActivities, final CacheAllItemsInteractorResponse response) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MadridActivityDAO dao = new MadridActivityDAO(context);

                boolean success = true;
                for (MadridActivity madridActivity: madridActivities.allItems()) {
                    success = dao.insert(madridActivity) > 0;
                    if (!success) {
                        break;
                    }
                }

                Looper main = Looper.getMainLooper();
                // TODO: put on Main Thread
                if (response != null) {
                    response.response(success);
                }

            }
        }).start();
    }
}
