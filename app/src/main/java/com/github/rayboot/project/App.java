package com.github.rayboot.project;

import android.app.Application;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.tumblr.remember.Remember;
import com.umeng.analytics.AnalyticsConfig;

/**
 * author: rayboot  Created on 15/12/3.
 * email : sy0725work@gmail.com
 */
public class App extends Application {
    private static App ourInstance = new App();

    public static App getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //数据库ORM
        FlowManager.init(this);

        //shared preferences,
        Remember.init(this, BuildConfig.APPLICATION_ID + "_remember");

        //友盟key
        AnalyticsConfig.setAppkey(this, "");

    }

}
