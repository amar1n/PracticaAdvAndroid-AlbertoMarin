package io.keepcoding.madridguide.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.fragments.MadridActivitiesFragment;
import io.keepcoding.madridguide.interactors.GetAllMadridActivitiesFromLocalCacheInteractor;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;
import io.keepcoding.madridguide.navigator.Navigator;
import io.keepcoding.madridguide.util.OnElementClick;

public class MadridActivitiesActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MadridActivitiesFragment madridActivitiesFragment;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madridactivities);

        madridActivitiesFragment = (MadridActivitiesFragment) getFragmentManager().findFragmentById(R.id.activity_madridactivities_fragment_madridactivities);
        initilizeMap();

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

    private void initilizeMap() {
        if (mapFragment == null) {
            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            // check if map is created successfully or not
            if (mapFragment == null) {
                Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_LONG).show();
            } else {
                mapFragment.getMapAsync(this);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(40.43807216375375, -3.6795366500000455)).zoom(11).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//        // Add a marker in Sydney, Australia, and move the camera.
//        LatLng sydney = new LatLng(-34, 151);
//        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
