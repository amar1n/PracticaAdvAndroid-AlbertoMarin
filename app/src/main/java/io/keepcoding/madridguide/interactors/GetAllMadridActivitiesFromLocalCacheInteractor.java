package io.keepcoding.madridguide.interactors;

import android.content.Context;

import java.util.List;

import io.keepcoding.madridguide.manager.db.MadridActivityDAO;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;
import io.keepcoding.madridguide.util.MainThread;

public class GetAllMadridActivitiesFromLocalCacheInteractor {

    public interface OnGetAllMadridActivitiesFromLocalCacheInteractorCompletion {
        void completion(MadridActivities madridActivities);
    }

    public void execute(final Context context, final OnGetAllMadridActivitiesFromLocalCacheInteractorCompletion completion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MadridActivityDAO dao = new MadridActivityDAO(context);

                List<MadridActivity> madridActivityList = dao.query();
                final MadridActivities madridActivities = MadridActivities.build(madridActivityList);

                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        completion.completion(madridActivities);
                    }
                });


            }
        }).start();
    }
}
