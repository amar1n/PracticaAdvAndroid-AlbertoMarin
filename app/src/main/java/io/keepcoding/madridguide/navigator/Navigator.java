package io.keepcoding.madridguide.navigator;

import android.content.Intent;

import io.keepcoding.madridguide.activities.MadridActivitiesActivity;
import io.keepcoding.madridguide.activities.MadridActivityDetailActivity;
import io.keepcoding.madridguide.activities.MainActivity;
import io.keepcoding.madridguide.activities.ShopDetailActivity;
import io.keepcoding.madridguide.activities.ShopsActivity;
import io.keepcoding.madridguide.model.MadridActivity;
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

    public static Intent navigateFromMainActivityToMadridActivitiesActivity(MainActivity mainActivity) {
        Intent i = new Intent(mainActivity, MadridActivitiesActivity.class);
        mainActivity.startActivity(i);
        return i;
    }

    public static Intent navigateFromMadridActivitiesActivityToMadridActivityDetailActivity(final MadridActivitiesActivity madirdActivitiesActivity, MadridActivity detail) {
        final Intent i = new Intent(madirdActivitiesActivity, MadridActivityDetailActivity.class);

        i.putExtra(Constants.INTENT_KEY_MADRIDACTIVITY_DETAIL, detail);

        madirdActivitiesActivity.startActivity(i);

        return i;
    }
}
