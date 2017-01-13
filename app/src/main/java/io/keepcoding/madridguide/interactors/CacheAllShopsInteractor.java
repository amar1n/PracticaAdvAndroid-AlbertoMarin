package io.keepcoding.madridguide.interactors;

import android.content.Context;
import android.content.SharedPreferences;

import org.joda.time.DateTime;

import io.keepcoding.madridguide.manager.db.ShopDAO;
import io.keepcoding.madridguide.model.Shop;
import io.keepcoding.madridguide.model.Shops;
import io.keepcoding.madridguide.util.Constants;
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

                if (bFlag) {
                    final SharedPreferences preferences = context.getSharedPreferences(Constants.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(Constants.LAST_SHOPS_DOWNLOAD_KEY, DateTime.now().toString());
                    editor.commit();
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
