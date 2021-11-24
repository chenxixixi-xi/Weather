package com.nicechina.weather.model;

import android.util.Log;

import com.nicechina.weather.R;


public class Sky {
    private String info;
    private int icon;
    private int bg;

    public Sky(String info,int icon,int bg){
        this.info = info;
        this.icon = icon;
        this.bg = bg;
    }

    private static final Sky CLEAR_DAY = new Sky("sunny", R.drawable.ic_clear_day,R.drawable.bg_clear_day);
    private static final Sky CLEAR_NIGHT = new  Sky("sunny", R.drawable.ic_clear_night, R.drawable.bg_clear_night);
    private static final Sky PARTLY_CLOUDY_DAY = new  Sky("cloudy", R.drawable.ic_partly_cloud_day, R.drawable.bg_partly_cloudy_day);
    private static final Sky PARTLY_CLOUDY_NIGHT = new  Sky("cloudy", R.drawable.ic_partly_cloud_night, R.drawable.ic_partly_cloud_night);
    private static final Sky CLOUDY = new  Sky("overcast", R.drawable.ic_cloudy, R.drawable.bg_cloudy);
    private static final Sky WIND = new  Sky("gale", R.drawable.ic_cloudy, R.drawable.bg_wind);
    private static final Sky LIGHT_RAIN = new  Sky("light rain", R.drawable.ic_light_rain, R.drawable.bg_rain);
    private static final Sky MODERATE_RAIN = new  Sky("moderate rain", R.drawable.ic_moderate_rain, R.drawable.bg_rain);
    private static final Sky HEAVY_RAIN = new  Sky("heavy rain", R.drawable.ic_heavy_rain, R.drawable.bg_rain);
    private static final Sky STORM_RAIN = new  Sky("rainstorm", R.drawable.ic_storm_rain, R.drawable.bg_rain);
    private static final Sky THUNDER_SHOWER = new Sky("thunder shower", R.drawable.ic_thunder_shower, R.drawable.bg_rain);
    private static final Sky SLEET = new  Sky("sleet", R.drawable.ic_sleet, R.drawable.bg_rain);
    private static final Sky LIGHT_SNOW = new  Sky("light snow", R.drawable.ic_light_snow, R.drawable.bg_snow);
    private static final Sky MODERATE_SNOW = new  Sky("moderate snow", R.drawable.ic_moderate_snow, R.drawable.bg_snow);
    private static final Sky HEAVY_SNOW = new  Sky("heavy snow", R.drawable.ic_heavy_snow, R.drawable.bg_snow);
    private static final Sky STORM_SNOW = new Sky("Blizzard", R.drawable.ic_heavy_snow, R.drawable.bg_snow);
    private static final Sky HAIL = new Sky("hail", R.drawable.ic_hail, R.drawable.bg_snow);
    private static final Sky LIGHT_HAZE = new  Sky("Mild haze", R.drawable.ic_light_haze, R.drawable.bg_fog);
    private static final Sky MODERATE_HAZE = new Sky("Moderate haze", R.drawable.ic_moderate_haze, R.drawable.bg_fog);
    private static final Sky HEAVY_HAZE = new  Sky("Severe haze", R.drawable.ic_heavy_haze, R.drawable.bg_fog);
    private static final Sky FOG = new  Sky("fog", R.drawable.ic_fog, R.drawable.bg_fog);
    private static final Sky DUST = new Sky("Floating dust", R.drawable.ic_fog, R.drawable.bg_fog);

    public static Sky getSky(String skycon){
        switch (skycon){
            case "CLEAR_DAY":
                return CLEAR_DAY;

            case "CLEAR_NIGHT":
                return CLEAR_NIGHT;

            case  "PARTLY_CLOUDY_DAY":
                return PARTLY_CLOUDY_DAY;

            case "PARTLY_CLOUDY_NIGHT":
                return PARTLY_CLOUDY_NIGHT;

            case "CLOUDY":
                return CLOUDY;

            case "WIND":
                return WIND;

            case "LIGHT_RAIN":
                return LIGHT_RAIN;

            case "MODERATE_RAIN":
                return MODERATE_RAIN;

            case "HEAVY_RAIN":
                return HEAVY_RAIN;

            case "STORM_RAIN":
                return STORM_RAIN;

            case "THUNDER_SHOWER":
                return THUNDER_SHOWER;

            case "SLEET":
                return SLEET;

            case "LIGHT_SNOW":
                return LIGHT_SNOW;

            case "MODERATE_SNOW":
                return MODERATE_SNOW;

            case "HEAVY_SNOW":
                return HEAVY_SNOW;

            case "STORM_SNOW":
                return STORM_SNOW;

            case "HAIL":
                return HAIL;

            case "LIGHT_HAZE":
                return LIGHT_HAZE;

            case "MODERATE_HAZE":
                return MODERATE_HAZE;

            case "HEAVY_HAZE":
                return HEAVY_HAZE;

            case "FOG":
                return FOG;

            case "DUST":
                return DUST;

            default:
                Log.d("Sky","Sky information wrong!");
        }
        return null;//这里是默认返回值，可能需要修改
    }

    public String getInfo() {
        return info;
    }

    public int getIcon() {
        return icon;
    }

    public int getBg() {
        return bg;
    }
}