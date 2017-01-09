package io.keepcoding.madridguide;

import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.List;

import io.keepcoding.madridguide.manager.db.MadridActivityDAO;
import io.keepcoding.madridguide.model.MadridActivity;


public class ActivityDAOTests extends AndroidTestCase {
    public static final String MADRIDACTIVITY_TESTING_NAME = "MadridActivity testing name";
    public static final String ADDRESS_TESTING = "Address testing";

    public void testCanInsertANewMadridActivity() {
        final MadridActivityDAO sut = new MadridActivityDAO(getContext());

        final int count = getCount(sut);

        final long id = insertTestMadridActivity(sut);

        assertTrue(id > 0);
        assertTrue(count + 1 == sut.queryCursor().getCount());
    }

    public void testCanDeleteAMadridActivity() {
        final MadridActivityDAO sut = new MadridActivityDAO(getContext());

        final long id = insertTestMadridActivity(sut);

        final int count = getCount(sut);

        assertEquals(1, sut.delete(id));

        assertTrue(count - 1 == sut.queryCursor().getCount());
    }

    public void testDeleteAll() {
        final MadridActivityDAO sut = new MadridActivityDAO(getContext());

        sut.deleteAll();

        final int count = getCount(sut);
        assertEquals(0, count);
    }

    public void testQueryOneMadridActivity() {
        final MadridActivityDAO dao = new MadridActivityDAO(getContext());

        final long id = insertTestMadridActivity(dao);

        MadridActivity sut = dao.query(id);
        assertNotNull(sut);
        assertEquals(sut.getName(), MADRIDACTIVITY_TESTING_NAME);
    }

    public void testQueryAllMadridActivities() {
        final MadridActivityDAO dao = new MadridActivityDAO(getContext());

        final long id = insertTestMadridActivity(dao);

        List<MadridActivity> madridActivityList = dao.query();
        assertNotNull(madridActivityList);
        assertTrue(madridActivityList.size() > 0);

        for (MadridActivity madridActivity : madridActivityList) {
            assertTrue(madridActivity.getName().length() > 0);
        }
    }



    private int getCount(MadridActivityDAO sut) {
        final Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }

    private long insertTestMadridActivity(MadridActivityDAO sut) {
        final MadridActivity madridActivity = new MadridActivity(1, MADRIDACTIVITY_TESTING_NAME).setAddress(ADDRESS_TESTING);
        return sut.insert(madridActivity);
    }

}
