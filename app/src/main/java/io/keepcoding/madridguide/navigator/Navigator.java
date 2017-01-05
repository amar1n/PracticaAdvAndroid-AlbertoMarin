package io.keepcoding.madridguide.navigator;

import android.content.Intent;

import io.keepcoding.madridguide.activities.MainActivity;
import io.keepcoding.madridguide.activities.ShopDetailActivity;
import io.keepcoding.madridguide.activities.ShopsActivity;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.util.Constants;

public class Navigator {
    public static Intent navigateFromMainActivityToShopsActivity(MainActivity mainActivity) {
        Intent i = new Intent(mainActivity, ShopsActivity.class);
        mainActivity.startActivity(i);
        return i;
    }

    public static Intent navigateFromShopsActivityToShopDetailActivity(final ShopsActivity shopsActivity, Shop detail) {
        final Intent i = new Intent(shopsActivity, ShopDetailActivity.class);

        i.putExtra(Constants.INTENT_KEY_SHOP_DETAIL, detail);

        shopsActivity.startActivity(i);

        return i;
    }

}
