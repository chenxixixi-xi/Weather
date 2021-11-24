package com.nicechina.weather;

import android.app.Application;
import android.content.Context;

import com.nicechina.weather.dao.PlaceDao;

public class SunnyWeatherApplication extends Application {

    public static final String TOKEN = "VKC3aGqblsPQTRQT";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        PlaceDao.getData();
    }

    public static Context getContext() {
        return context;
    }

}
