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
import io.keepcoding.madridguide.fragments.ShopsFragment;
import io.keepcoding.madridguide.interactors.GetAllShopsFromLocalCacheInteractor;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.navigator.Navigator;
import io.keepcoding.madridguide.util.Constants;
import io.keepcoding.madridguide.util.OnElementClick;

public class ShopsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ShopsFragment shopsFragment;
    private SupportMapFragment mapFragment;
    private Shops shops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        shopsFragment = (ShopsFragment) getFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);

        GetAllShopsFromLocalCacheInteractor interactor = new GetAllShopsFromLocalCacheInteractor();
        interactor.execute(this, new GetAllShopsFromLocalCacheInteractor.OnGetAllShopsFromLocalCacheInteractorCompletion() {
            @Override
            public void completion(Shops shops) {
                ShopsActivity.this.shops = shops;
                shopsFragment.setListener(new OnElementClick<Shop>() {
                    @Override
                    public void clickedOn(@NonNull Shop shop, int position) {
                        Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
                    }
                });

                shopsFragment.setShops(shops);
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
                final Shop shop = (Shop) data.get(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_ITEM);
                Boolean bFlag = (Boolean) data.get(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_FLAG);

                if (bFlag) {
                    Picasso.with(ShopsActivity.this)
                            .load(shop.getLogoImgUrl())
                            .networkPolicy(NetworkPolicy.OFFLINE)
                            .into(logo, new Callback() {
                                @Override
                                public void onSuccess() {
                                    HashMap<String, Object> data = new HashMap<>();
                                    data.put(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_ITEM, shop);
                                    data.put(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_FLAG, Boolean.FALSE);
                                    marker.setTag(data);
                                    marker.showInfoWindow();
                                }

                                @Override
                                public void onError() {

                                }
                            });
                } else {
                    Picasso.with(ShopsActivity.this)
                            .load(shop.getLogoImgUrl())
                            .networkPolicy(NetworkPolicy.OFFLINE)
                            .into(logo);
                }
                name.setText(shop.getName());

                return v;
            }
        });


        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                final HashMap data = (HashMap) marker.getTag();
                final Shop shop = (Shop) data.get(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_ITEM);
                Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
            }
        });

        for (Shop shop: shops.allItems()) {
            LatLng latLng = new LatLng(shop.getLatitude(), shop.getLongitude());
            Marker marker = googleMap.addMarker(new MarkerOptions().position(latLng).title(shop.getName()));
            HashMap<String, Object> data = new HashMap<>();
            data.put(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_ITEM, shop);
            data.put(Constants.MAP_INFOWINDOW_MARKER_PICASSO_CALLBACK_FLAG, Boolean.TRUE);
            marker.setTag(data);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }

        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(40.43807216375375, -3.6795366500000455)).zoom(11).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
