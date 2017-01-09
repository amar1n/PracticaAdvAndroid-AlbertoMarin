package io.keepcoding.madridguide.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.fragments.ActivitiesFragment;
import io.keepcoding.madridguide.interactors.GetAllActivitiesFromLocalCacheInteractor;
import io.keepcoding.madridguide.model.Activities;
import io.keepcoding.madridguide.model.Activity;
import io.keepcoding.madridguide.navigator.Navigator;
import io.keepcoding.madridguide.util.OnElementClick;

public class ActivitiesActivity extends AppCompatActivity {

    private ActivitiesFragment activitiesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        activitiesFragment = (ActivitiesFragment) getFragmentManager().findFragmentById(R.id.activity_activities_fragment_activities);

        GetAllActivitiesFromLocalCacheInteractor interactor = new GetAllActivitiesFromLocalCacheInteractor();
        interactor.execute(this, new GetAllActivitiesFromLocalCacheInteractor.OnGetAllActivitiesFromLocalCacheInteractorCompletion() {
            @Override
            public void completion(Activities activities) {
                activitiesFragment.setListener(new OnElementClick<Activity>() {
                    @Override
                    public void clickedOn(@NonNull Activity activity, int position) {
                        Navigator.navigateFromActivitiesActivityToActivityDetailActivity(ActivitiesActivity.this, activity);
                    }
                });

                activitiesFragment.setActivities(activities);
            }
        });
    }
}
