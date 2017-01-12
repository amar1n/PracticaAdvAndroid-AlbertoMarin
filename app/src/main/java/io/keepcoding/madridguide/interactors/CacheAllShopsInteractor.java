package io.keepcoding.madridguide.interactors;

import android.content.Context;

import io.keepcoding.madridguide.manager.db.ShopDAO;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.util.MainThread;

public class CacheAllShopsInteractor {

    public void execute(final Context context, final Shops shops, final CacheAllItemsInteractorResponse response) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);

                boolean bFlag = true;
                for (Shop shop: shops.allItems()) {
                    bFlag = dao.insert(shop) > 0;
                    if (!bFlag) {
                        break;
                    }
                }

                final boolean success = bFlag;
                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        if (response != null) {
                            response.response(success);
                        }
                    }
                });
            }
        }).start();
    }
}
