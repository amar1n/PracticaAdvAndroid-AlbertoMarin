package io.keepcoding.madridguide;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import io.keepcoding.madridguide.manager.db.DBConstants;
import io.keepcoding.madridguide.manager.db.ShopDAO;
import io.keepcoding.madridguide.manager.db.provider.MadridGuideProvider;
import io.keepcoding.madridguide.model.Shop;

public class MadridGuideProviderTests extends AndroidTestCase {
    public void testQueryAllShops() {
        ContentResolver cr = getContext().getContentResolver();

        Cursor c = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS, null, null, null);
        assertNotNull(c);
    }

    public void testInsertAShop() {
        final ContentResolver cr = getContext().getContentResolver();

        final Cursor beforeCursor = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS, null, null, null);
        final int beforeCount = beforeCursor.getCount();

        final Shop shop = new Shop(1, "Little shop of horrors!");
        final Uri insertedUri = cr.insert(MadridGuideProvider.SHOPS_URI, ShopDAO.getContentValues(shop));
        assertNotNull(insertedUri);

        final Cursor afterCursor = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS, null, null, null);
        final int afterCount = afterCursor.getCount();

        assertEquals(beforeCount + 1, afterCount);
    }


}
