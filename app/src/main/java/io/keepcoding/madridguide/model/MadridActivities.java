package io.keepcoding.madridguide.model;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MadridActivities implements IItemsIterate<MadridActivity>, IItemsUpdate<MadridActivity> {

    private List<MadridActivity> madridActivities;

    private MadridActivities() {
    }

    public static
    @NonNull
    MadridActivities build(@NonNull List<MadridActivity> madridActivityList) {
        MadridActivities madridActivities = new MadridActivities();

        if (madridActivityList == null) {
            madridActivities.madridActivities = new ArrayList<>();
        } else {
            madridActivities.madridActivities = madridActivityList;
        }

        return madridActivities;
    }

    @Override
    public long size() {
        return madridActivities.size();
    }

    @Override
    public
    @Nullable
    MadridActivity get(long index) {
        if (index > size()) {
            return null;
        }
        return madridActivities.get((int) index);
    }

    @Override
    public
    @NonNull
    List<MadridActivity> allItems() {
        return this.madridActivities;
    }

    @Override
    public void add(MadridActivity madridActivity) {
        this.madridActivities.add(madridActivity);
    }

    @Override
    public void delete(MadridActivity madridActivity) {
        this.madridActivities.remove(madridActivity);
    }

    @Override
    public void edit(MadridActivity madridActivity, long index) {

    }
}
