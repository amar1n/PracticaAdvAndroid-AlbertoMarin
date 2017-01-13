package io.keepcoding.madridguide.interactors;

import android.content.Context;

import java.util.List;

import io.keepcoding.madridguide.manager.net.MadridActivityEntity;
import io.keepcoding.madridguide.manager.net.NetworkManager;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;
import io.keepcoding.madridguide.model.mappers.MadridActivityEntityMadridActivityMapper;
import io.keepcoding.madridguide.util.Constants;
import io.keepcoding.madridguide.util.MadridGuideUtils;

public class GetAllMadridActivitiesInteractor implements IGetAllItemsInteractor<MadridActivities> {
    public void execute(final Context context, final GetAllItemsInteractorResponse<MadridActivities> response) {

        MadridGuideUtils mgu = new MadridGuideUtils();
        boolean doTheDownload = mgu.doDownload(context, Constants.LAST_MADRIDACTIVITIES_DOWNLOAD_KEY);
        if (!doTheDownload) {
            GetAllMadridActivitiesFromLocalCacheInteractor interactor = new GetAllMadridActivitiesFromLocalCacheInteractor();
            interactor.execute(context, new GetAllMadridActivitiesFromLocalCacheInteractor.OnGetAllMadridActivitiesFromLocalCacheInteractorCompletion() {
                @Override
                public void completion(MadridActivities madridActivities) {
                    response.response(madridActivities);
                }
            });
            return;
        }

        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getMadridActivitiesFromServer(new NetworkManager.GetEntitiesListener<MadridActivityEntity>() {
            @Override
            public void getEntitiesSuccess(List<MadridActivityEntity> result) {
                List<MadridActivity> madridActivities = new MadridActivityEntityMadridActivityMapper().map(result);
                if (response != null) {
                    response.response(MadridActivities.build(madridActivities));
                }
            }

            @Override
            public void getEntitiesDidFail() {
                if (response != null) {
                    response.response(null);
                }
            }
        });
    }
}
