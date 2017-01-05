package io.keepcoding.madridguide;

import android.test.AndroidTestCase;

import io.keepcoding.madridguide.model.Activity;


public class ActivityTests extends AndroidTestCase {

    public static final String ACTIVITY = "activity";
    public static final String ADDRESS = "ADDRESS";

    public void testCanCreateAnActivity() {
        Activity sut = new Activity(0, ACTIVITY);
        assertNotNull(sut);
    }

    public void testANewActivityStoresDataCorrectly() {
        Activity sut = new Activity(10, ACTIVITY);
        assertEquals(10, sut.getId());
        assertEquals(ACTIVITY, sut.getName());
    }

    public void testANewActivityStoresDataInPropertiesCorrectly() {
        Activity sut = new Activity(11, ACTIVITY)
                    .setAddress(ADDRESS)
                    .setDescription("DESC")
                    .setImageUrl("URL");

        assertEquals(sut.getAddress(), ADDRESS);
        assertEquals(sut.getDescription(), "DESC");

    }
}
