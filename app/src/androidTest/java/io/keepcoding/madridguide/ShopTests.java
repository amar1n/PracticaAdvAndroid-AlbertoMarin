package io.keepcoding.madridguide;

import android.test.AndroidTestCase;

import io.keepcoding.madridguide.model.Shop;


public class ShopTests extends AndroidTestCase {

    public static final String SHOP = "shop";
    public static final String ADDRESS = "ADDRESS";

    public void testCanCreateAShop() {
        Shop sut = new Shop(0, SHOP);
        assertNotNull(sut);
    }

    public void testANewShopStoresDataCorrectly() {
        Shop sut = new Shop(10, SHOP);
        assertEquals(10, sut.getId());
        assertEquals(SHOP, sut.getName());
    }

    public void testANewShopStoresDataInPropertiesCorrectly() {
        Shop sut = new Shop(11, SHOP)
                    .setAddress(ADDRESS)
                    .setDescription("DESC")
                    .setImageUrl("URL");

        assertEquals(sut.getAddress(), ADDRESS);
        assertEquals(sut.getDescription(), "DESC");

    }
}
