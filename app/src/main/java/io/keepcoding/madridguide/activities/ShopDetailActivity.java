package io.keepcoding.madridguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.keepcoding.madridguide.R;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.util.Constants;
import io.keepcoding.madridguide.util.MadridGuideUtils;

public class ShopDetailActivity extends AppCompatActivity {
    @BindView(R.id.activity_shop_detail_shop_name_text)
    TextView shopNameText;

    @BindView(R.id.activity_shop_detail_shop_logo_image)
    ImageView shopLogoImage;

    @BindView(R.id.activity_shop_detail_shop_map_image)
    ImageView shopMapImage;

    Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        ButterKnife.bind(this);

        getDetailShopFromCallingIntent();

        updateUI();
    }

    private void getDetailShopFromCallingIntent() {
        Intent i = getIntent();
        if (i != null) {
            shop = (Shop) i.getSerializableExtra(Constants.INTENT_KEY_SHOP_DETAIL);
        }
    }

    private void updateUI() {
        shopNameText.setText(shop.getName());

        Picasso.with(this)
                .load(shop.getLogoImgUrl())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(shopLogoImage);

        Picasso.with(this)
                .load(MadridGuideUtils.getMapUrl(shop.getLatitude(), shop.getLongitude()))
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(shopMapImage);
    }
}
