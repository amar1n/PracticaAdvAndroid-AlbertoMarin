package io.keepcoding.madridguide.util;

import android.os.Handler;
import android.os.Looper;

public class MainThread {
    public static void run(Runnable codeToRun) {
        Handler uiHandler = new Handler(Looper.getMainLooper());

        uiHandler.post(codeToRun);
    }
}
