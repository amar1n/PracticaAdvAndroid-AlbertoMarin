package io.keepcoding.madridguide.interactors;

import android.content.Context;

import java.util.List;

import io.keepcoding.madridguide.manager.db.ShopDAO;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.util.MainThread;

public class GetAllShopsFromLocalCacheInteractor {

    public interface  OnGetAllShopsFromLocalCacheInteractorCompletion {
        public void completion(Shops shops);
    }

    public void execute(final Context context, final OnGetAllShopsFromLocalCacheInteractorCompletion completion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);

                List<Shop> shopList = dao.query();
                final Shops shops = Shops.build(shopList);

                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        completion.completion(shops);
                    }
                });


            }
        }).start();
    }
}
