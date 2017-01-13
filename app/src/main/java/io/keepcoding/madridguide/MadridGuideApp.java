package io.keepcoding.madridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

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

        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
