package io.keepcoding.madridguide.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.fragments.MadridActivitiesFragment;
import io.keepcoding.madridguide.interactors.GetAllMadridActivitiesFromLocalCacheInteractor;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;
import io.keepcoding.madridguide.navigator.Navigator;
import io.keepcoding.madridguide.util.Constants;
import io.keepcoding.madridguide.util.OnElementClick;

public class MadridActivitiesActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MadridActivitiesFragment madridActivitiesFragment;
    private SupportMapFragment mapFragment;
    private MadridActivities madridActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madridactivities);

        madridActivitiesFragment = (MadridActivitiesFragment) getFragmentManager().findFragmentById(R.id.activity_madridactivities_fragment_madridactivities);

        GetAllMadridActivitiesFromLocalCacheInteractor interactor = new GetAllMadridActivitiesFromLocalCacheInteractor();
        interactor.execute(this, new GetAllMadridActivitiesFromLocalCacheInteractor.OnGetAllMadridActivitiesFromLocalCacheInteractorCompletion() {
            @Override
            public void completion(MadridActivities madridActivities) {
                MadridActivitiesActivity.this.madridActivities = madridActivities;
                madridActivitiesFragment.setListener(new OnElementClick<MadridActivity>() {
                    @Override
                    public void clickedOn(@NonNull MadridActivity activity, int position) {
                        Navigator.navigateFromMadridActivitiesActivityToMadridActivityDetailActivity(MadridActivitiesActivity.this, activity);
                    }
                });

                madridActivitiesFragment.setMadridActivities(madridActivities);
                initilizeMap();
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
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(final Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.map_info_window, null);
                ImageView logo = (ImageView) v.findViewById(R.id.map_info_window_logo);
                TextView name = (TextView) v.findViewById(R.id.map_info_window_name);

                HashMap data = (HashMap) marker.getTag();
                final MadridActivity madridActivity = (MadridActivity) data.get(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_ITEM);
                Boolean bFlag = (Boolean) data.get(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_FLAG);

                if (bFlag) {
                    Picasso.with(MadridActivitiesActivity.this)
                            .load(madridActivity.getLogoImgUrl())
                            .networkPolicy(NetworkPolicy.OFFLINE)
                            .into(logo, new Callback() {
                                @Override
                                public void onSuccess() {
                                    HashMap<String, Object> data = new HashMap<>();
                                    data.put(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_ITEM, madridActivity);
                                    data.put(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_FLAG, Boolean.FALSE);
                                    marker.setTag(data);
                                    marker.showInfoWindow();
                                }

                                @Override
                                public void onError() {

                                }
                            });
                } else {
                    Picasso.with(MadridActivitiesActivity.this)
                            .load(madridActivity.getLogoImgUrl())
                            .networkPolicy(NetworkPolicy.OFFLINE)
                            .into(logo);
                }
                name.setText(madridActivity.getName());

                return v;
            }
        });

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                final HashMap data = (HashMap) marker.getTag();
                final MadridActivity madridActivity = (MadridActivity) data.get(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_ITEM);
                Navigator.navigateFromMadridActivitiesActivityToMadridActivityDetailActivity(MadridActivitiesActivity.this, madridActivity);
            }
        });

        for (MadridActivity madridActivity: madridActivities.allItems()) {
            LatLng latLng = new LatLng(madridActivity.getLatitude(), madridActivity.getLongitude());
            Marker marker = googleMap.addMarker(new MarkerOptions().position(latLng).title(madridActivity.getName()));
            HashMap<String, Object> data = new HashMap<>();
            data.put(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_ITEM, madridActivity);
            data.put(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_FLAG, Boolean.TRUE);
            marker.setTag(data);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }

        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(40.43807216375375, -3.6795366500000455)).zoom(11).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
