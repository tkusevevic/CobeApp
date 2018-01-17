package com.tkusevic.CobeApp;

import android.app.Application;

/**
 * Created by tkusevic on 15.01.2018..
 */

public class App extends Application {

    private static final Data data = new Data();

    public static Data getData() {
        return data;
    }

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
    }

    private static void setInstance(App app) {
        instance = app;
    }

    public static App getInstance() {
        return instance;
    }


}
