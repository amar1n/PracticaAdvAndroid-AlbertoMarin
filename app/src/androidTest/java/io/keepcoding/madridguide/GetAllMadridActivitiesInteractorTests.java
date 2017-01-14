package io.keepcoding.madridguide;

import android.test.AndroidTestCase;

import io.keepcoding.madridguide.interactors.GetAllItemsInteractorResponse;
import io.keepcoding.madridguide.interactors.GetAllMadridActivitiesInteractorFakeImpl;
import io.keepcoding.madridguide.interactors.IGetAllItemsInteractor;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.MadridActivity;

// deprecated: https://developer.android.com/topic/libraries/testing-support-library/index.html
public class GetAllMadridActivitiesInteractorTests extends AndroidTestCase {

    public void testCanCreateInteractor() {
        IGetAllItemsInteractor<MadridActivities> getAllShopsInteractor = new GetAllMadridActivitiesInteractorFakeImpl();

        getAllShopsInteractor.execute(getContext(), new GetAllItemsInteractorResponse<MadridActivities>() {
            @Override
            public void response(MadridActivities madridActivities, boolean newItems) {
                assertNotNull(madridActivities);
                assertTrue(madridActivities.size() > 0);

                MadridActivity madridActivity = madridActivities.get(1);
                assertEquals(madridActivity.getId(), 2);
            }
        });
    }

}
