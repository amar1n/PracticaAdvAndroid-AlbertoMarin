package io.keepcoding.madridguide.interactors;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;
import io.keepcoding.madridguide.util.MainThread;


public class GetAllMadridActivitiesInteractorFakeImpl implements IGetAllItemsInteractor<MadridActivities> {
    @NonNull
    private List<MadridActivity> getMadridActivities() {
        List<MadridActivity> data = new ArrayList<>();
        data.add(new MadridActivity(1, "1").setAddress("AD 1"));
        data.add(new MadridActivity(2, "2").setAddress("AD 2"));
        return data;
    }

    @Override
    public void execute(Context context, final GetAllItemsInteractorResponse<MadridActivities> response) {
        List<MadridActivity> data = getMadridActivities();

        final MadridActivities sut = MadridActivities.build(data);

        // simulate connection
        try {
            Thread.currentThread().sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MainThread.run(new Runnable() {
            @Override
            public void run() {
                response.response(sut, false);
            }
        });
    }
}
