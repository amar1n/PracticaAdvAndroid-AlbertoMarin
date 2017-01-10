package io.keepcoding.madridguide.interactors;

import android.content.Context;

import java.util.List;

import io.keepcoding.madridguide.manager.net.NetworkManager;
import io.keepcoding.madridguide.manager.net.ShopEntity;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.model.mappers.ShopEntityShopMapper;

public class GetAllShopsInteractor implements IGetAllItemsInteractor<Shops> {
    public void execute(final Context context, final GetAllItemsInteractorResponse<Shops> response) {
        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getShopsFromServer(new NetworkManager.GetEntitiesListener<ShopEntity>() {
            @Override
            public void getEntitiesSuccess(List<ShopEntity> result) {
                List<Shop> shops = new ShopEntityShopMapper().map(result);
                if (response != null) {
                    response.response(Shops.build(shops));
                }
            }

            @Override
            public void getEntitiesDidFail() {
                if (response != null) {
                    response.response(null);
                }
            }
        });
    }
}
