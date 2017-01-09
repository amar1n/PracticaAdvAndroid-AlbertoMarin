package io.keepcoding.madridguide;

import android.test.AndroidTestCase;

import io.keepcoding.madridguide.model.MadridActivity;


public class ActivityTests extends AndroidTestCase {

    public static final String MADRIDACTIVITY = "madridActivity";
    public static final String ADDRESS = "ADDRESS";

    public void testCanCreateAnActivity() {
        MadridActivity sut = new MadridActivity(0, MADRIDACTIVITY);
        assertNotNull(sut);
    }

    public void testANewMAdridActivityStoresDataCorrectly() {
        MadridActivity sut = new MadridActivity(10, MADRIDACTIVITY);
        assertEquals(10, sut.getId());
        assertEquals(MADRIDACTIVITY, sut.getName());
    }

    public void testANewMadridActivityStoresDataInPropertiesCorrectly() {
        MadridActivity sut = new MadridActivity(11, MADRIDACTIVITY)
                    .setAddress(ADDRESS)
                    .setDescription("DESC")
                    .setImageUrl("URL");

        assertEquals(sut.getAddress(), ADDRESS);
        assertEquals(sut.getDescription(), "DESC");

    }
}
