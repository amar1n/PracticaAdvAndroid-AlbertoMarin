package io.keepcoding.madridguide.interactors;

import android.content.Context;

import java.util.List;

import io.keepcoding.madridguide.manager.net.NetworkManager;
import io.keepcoding.madridguide.manager.net.ShopEntity;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.model.mappers.ShopEntityShopMapper;
import io.keepcoding.madridguide.util.Constants;
import io.keepcoding.madridguide.util.MadridGuideUtils;

public class GetAllShopsInteractor implements IGetAllItemsInteractor<Shops> {
    public void execute(final Context context, final GetAllItemsInteractorResponse<Shops> response) {

        boolean doTheDownload = MadridGuideUtils.doDownload(context, Constants.LAST_SHOPS_DOWNLOAD_KEY);
        if (!doTheDownload) {
            GetAllShopsFromLocalCacheInteractor interactor = new GetAllShopsFromLocalCacheInteractor();
            interactor.execute(context, new GetAllShopsFromLocalCacheInteractor.OnGetAllShopsFromLocalCacheInteractorCompletion() {
                @Override
                public void completion(Shops shops) {
                    response.response(shops, false);
                }
            });
            return;
        }

        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getShopsFromServer(new NetworkManager.GetEntitiesListener<ShopEntity>() {
            @Override
            public void getEntitiesSuccess(List<ShopEntity> result) {
                List<Shop> shops = new ShopEntityShopMapper().map(result);
                if (response != null) {
                    response.response(Shops.build(shops), true);
                }
            }

            @Override
            public void getEntitiesDidFail() {
                if (response != null) {
                    response.response(null, false);
                }
            }
        });
    }
}
