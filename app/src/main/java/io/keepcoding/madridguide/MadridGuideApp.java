package io.keepcoding.madridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import io.keepcoding.madridguide.interactors.CacheAllItemsInteractorResponse;
import io.keepcoding.madridguide.interactors.CacheAllMadridActivitiesInteractor;
import io.keepcoding.madridguide.interactors.CacheAllShopsInteractor;
import io.keepcoding.madridguide.interactors.GetAllItemsInteractorResponse;
import io.keepcoding.madridguide.interactors.GetAllMadridActivitiesInteractor;
import io.keepcoding.madridguide.interactors.GetAllShopsInteractor;
import io.keepcoding.madridguide.model.MadridActivities;
import io.keepcoding.madridguide.model.Shops;

public class MadridGuideApp extends Application {
    private static WeakReference<Context> appContext;

    public static Context getAppContext() {
        if (appContext != null) {
            return MadridGuideApp.appContext.get();
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MadridGuideApp.appContext = new WeakReference<>(getApplicationContext());

        new GetAllShopsInteractor().execute(getApplicationContext(),
                new GetAllItemsInteractorResponse<Shops>() {
                    @Override
                    public void response(Shops shops) {
                        new CacheAllShopsInteractor().execute(getApplicationContext(),
                                shops, new CacheAllItemsInteractorResponse() {
                                    @Override
                                    public void response(boolean success) {
                                        // success, nothing to do here
                                    }
                                });
                    }
                }
        );

        new GetAllMadridActivitiesInteractor().execute(getApplicationContext(),
                new GetAllItemsInteractorResponse<MadridActivities>() {
                    @Override
                    public void response(MadridActivities madridActivities) {
                        new CacheAllMadridActivitiesInteractor().execute(getApplicationContext(),
                                madridActivities, new CacheAllItemsInteractorResponse() {
                                    @Override
                                    public void response(boolean success) {
                                        // success, nothing to do here
                                    }
                                });
                    }
                }
        );

        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
