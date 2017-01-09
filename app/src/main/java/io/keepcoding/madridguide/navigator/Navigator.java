package io.keepcoding.madridguide.navigator;

import android.content.Intent;

import io.keepcoding.madridguide.activities.ActivitiesActivity;
import io.keepcoding.madridguide.activities.ActivityDetailActivity;
import io.keepcoding.madridguide.activities.MainActivity;
import io.keepcoding.madridguide.activities.ShopDetailActivity;
import io.keepcoding.madridguide.activities.ShopsActivity;
import io.keepcoding.madridguide.model.Activity;
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

    public static Intent navigateFromMainActivityToActivitiesActivity(MainActivity mainActivity) {
        Intent i = new Intent(mainActivity, ActivitiesActivity.class);
        mainActivity.startActivity(i);
        return i;
    }

    public static Intent navigateFromActivitiesActivityToActivityDetailActivity(final ActivitiesActivity activitiesActivity, Activity detail) {
        final Intent i = new Intent(activitiesActivity, ActivityDetailActivity.class);

        i.putExtra(Constants.INTENT_KEY_SHOP_DETAIL, detail);

        activitiesActivity.startActivity(i);

        return i;
    }
}
