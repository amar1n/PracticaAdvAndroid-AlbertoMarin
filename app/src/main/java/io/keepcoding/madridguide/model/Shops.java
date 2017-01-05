package io.keepcoding.madridguide.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Shops implements IShopsIterate, IShopsUpdate {

    private List<Shop> shops;

    public static @NonNull Shops build(@NonNull List<Shop> shopList) {
        Shops shops = new Shops();

        if (shopList == null) {
            shops.shops = new ArrayList<>();
        } else {
            shops.shops = shopList;
        }

        return shops;
    }

    private Shops() {
    }

    @Override
    public long size() {
        return shops.size();
    }

    @Override
    public @Nullable Shop get(long index) {
        if (index > size()) {
            return null;
        }
        return shops.get((int)index);
    }

    @Override
    public @NonNull  List<Shop> allShops() {
        return this.shops;
    }

    @Override
    public void add(Shop shop) {
        this.shops.add(shop);
    }

    @Override
    public void delete(Shop shop) {
        this.shops.remove(shop);
    }

    @Override
    public void edit(Shop shop, long index) {

    }
}
