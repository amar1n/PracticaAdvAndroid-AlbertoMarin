package io.keepcoding.madridguide;

import android.support.annotation.NonNull;
import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;

import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;


public class ActivitiesTests extends AndroidTestCase {
    public void testCreatingAMadridActivitiesWithNullListReturnsNonNullMadridActivities() {
        MadridActivities sut = MadridActivities.build(null);
        assertNotNull(sut);
        assertNotNull(sut.allItems());
    }

    public void testCreatingAMadridActivitiesWithAListReturnsNonNullMadridActivities() {
        List<MadridActivity> data = getMadridActivities();

        MadridActivities sut = MadridActivities.build(data);
        assertNotNull(sut);
        assertNotNull(sut.allItems());
        assertEquals(sut.allItems(), data);
        assertEquals(sut.allItems().size(), data.size());
    }

    @NonNull
    private List<MadridActivity> getMadridActivities() {
        List<MadridActivity> data = new ArrayList<>();
        data.add(new MadridActivity(1, "1").setAddress("AD 1"));
        data.add(new MadridActivity(2, "2").setAddress("AD 2"));
        return data;
    }

}
