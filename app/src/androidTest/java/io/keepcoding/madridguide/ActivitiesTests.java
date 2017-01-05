package io.keepcoding.madridguide;

import android.support.annotation.NonNull;
import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;

import io.keepcoding.madridguide.model.Activities;
import io.keepcoding.madridguide.model.Activity;


public class ActivitiesTests extends AndroidTestCase {
    public void testCreatingAnActivitiesWithNullListReturnsNonNullActivities() {
        Activities sut = Activities.build(null);
        assertNotNull(sut);
        assertNotNull(sut.allItems());
    }

    public void testCreatingAnActivitiesWithAListReturnsNonNullActivities() {
        List<Activity> data = getActivities();

        Activities sut = Activities.build(data);
        assertNotNull(sut);
        assertNotNull(sut.allItems());
        assertEquals(sut.allItems(), data);
        assertEquals(sut.allItems().size(), data.size());
    }

    @NonNull
    private List<Activity> getActivities() {
        List<Activity> data = new ArrayList<>();
        data.add(new Activity(1, "1").setAddress("AD 1"));
        data.add(new Activity(2, "2").setAddress("AD 2"));
        return data;
    }

}
