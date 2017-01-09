package io.keepcoding.madridguide.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.fragments.MadridActivitiesFragment;
import io.keepcoding.madridguide.interactors.GetAllMadridActivitiesFromLocalCacheInteractor;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;
import io.keepcoding.madridguide.navigator.Navigator;
import io.keepcoding.madridguide.util.OnElementClick;

public class MadridActivitiesActivity extends AppCompatActivity {

    private MadridActivitiesFragment madridActivitiesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madridactivities);

        madridActivitiesFragment = (MadridActivitiesFragment) getFragmentManager().findFragmentById(R.id.activity_madridactivities_fragment_madridactivities);

        GetAllMadridActivitiesFromLocalCacheInteractor interactor = new GetAllMadridActivitiesFromLocalCacheInteractor();
        interactor.execute(this, new GetAllMadridActivitiesFromLocalCacheInteractor.OnGetAllMadridActivitiesFromLocalCacheInteractorCompletion() {
            @Override
            public void completion(MadridActivities madridActivities) {
                madridActivitiesFragment.setListener(new OnElementClick<MadridActivity>() {
                    @Override
                    public void clickedOn(@NonNull MadridActivity activity, int position) {
                        Navigator.navigateFromMadridActivitiesActivityToMadridActivityDetailActivity(MadridActivitiesActivity.this, activity);
                    }
                });

                madridActivitiesFragment.setMadridActivities(madridActivities);
            }
        });
    }
}
