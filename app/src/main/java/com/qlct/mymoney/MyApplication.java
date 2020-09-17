package com.mvvmsample;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * Created by hirenpatel on 05/10/17.
 */

public class MyApplication extends Application {

    private static volatile MyApplication mApp;
    private Handler mMainThreadHandler;
    private Handler mBackgroundHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        mMainThreadHandler = new Handler(Looper.getMainLooper());
        HandlerThread bgThread = new HandlerThread("ViewModelBackground");
        bgThread.start();
        mBackgroundHandler = new Handler(bgThread.getLooper());
    }

    public static void runOnUiThread(Runnable r) {
        mApp.mMainThreadHandler.post(r);
    }

    public static void runInBackground(Runnable r) {
        mApp.mBackgroundHandler.post(r);
    }
}
