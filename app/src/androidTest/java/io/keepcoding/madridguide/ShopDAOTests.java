package io.keepcoding.madridguide;

import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.List;

import io.keepcoding.madridguide.manager.db.ShopDAO;
import io.keepcoding.madridguide.model.Shop;


public class ShopDAOTests extends AndroidTestCase {
    public static final String SHOP_TESTING_NAME = "Shop testing name";
    public static final String ADDRESS_TESTING = "Address testing";

    public void testCanInsertANewShop() {
        final ShopDAO sut = new ShopDAO(getContext());

        final int count = getCount(sut);

        final long id = insertTestShop(sut);

        assertTrue(id > 0);
        assertTrue(count + 1 == sut.queryCursor().getCount());
    }

    public void testCanDeleteAShop() {
        final ShopDAO sut = new ShopDAO(getContext());

        final long id = insertTestShop(sut);

        final int count = getCount(sut);

        assertEquals(1, sut.delete(id));

        assertTrue(count - 1 == sut.queryCursor().getCount());
    }

    public void testDeleteAll() {
        final ShopDAO sut = new ShopDAO(getContext());

        sut.deleteAll();

        final int count = getCount(sut);
        assertEquals(0, count);
    }

    public void testQueryOneShop() {
        final ShopDAO dao = new ShopDAO(getContext());

        final long id = insertTestShop(dao);

        Shop sut = dao.query(id);
        assertNotNull(sut);
        assertEquals(sut.getName(), SHOP_TESTING_NAME);
    }

    public void testQueryAllShops() {
        final ShopDAO dao = new ShopDAO(getContext());

        final long id = insertTestShop(dao);

        List<Shop> shopList = dao.query();
        assertNotNull(shopList);
        assertTrue(shopList.size() > 0);

        for (Shop shop : shopList) {
            assertTrue(shop.getName().length() > 0);
        }
    }



    private int getCount(ShopDAO sut) {
        final Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }

    private long insertTestShop(ShopDAO sut) {
        final Shop shop = new Shop(1, SHOP_TESTING_NAME).setAddress(ADDRESS_TESTING);
        return sut.insert(shop);
    }

}
