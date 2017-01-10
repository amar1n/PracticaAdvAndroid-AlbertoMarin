package io.keepcoding.madridguide.interactors;

import android.content.Context;

import java.util.List;

import io.keepcoding.madridguide.manager.net.MadridActivityEntity;
import io.keepcoding.madridguide.manager.net.NetworkManager;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;
import io.keepcoding.madridguide.model.mappers.MadridActivityEntityMadridActivityMapper;

public class GetAllMadridActivitiesInteractor implements IGetAllItemsInteractor<MadridActivities> {
    public void execute(final Context context, final GetAllItemsInteractorResponse<MadridActivities> response) {
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
