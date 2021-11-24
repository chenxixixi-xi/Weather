package com.nicechina.weather.network;

import static java.lang.Thread.sleep;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.nicechina.weather.dao.PlaceDao;
import com.nicechina.weather.model.DailyResponse;
import com.nicechina.weather.model.PlaceResponse;
import com.nicechina.weather.model.RealtimeResponse;
import com.nicechina.weather.model.Weather;

import java.util.List;


public class Repository {
    private static List<PlaceResponse.Place> places;

    private static MutableLiveData<List<PlaceResponse.Place>> placesData =
            new MutableLiveData<>();
    final static SunnyWeatherNetwork sunnyWeatherNetwork = new SunnyWeatherNetwork();

    //由于是静态方法，创建时就会调用一次
    public MutableLiveData<List<PlaceResponse.Place>> searchPlaces(String query) {

        new Thread(() -> {
            try {
                /*获取到搜索到的数据，在network层已经进行判空处理，保证了接收到的placeResponse不为null
                 * 但由于网络请求的同步问题，每次发出的第一个请求的结果，会导致placeResponse为 null，
                 * 因此在PlaceResponse中，需要new 一个 status和result，避免空指针异常。
                 * */
                final PlaceResponse placeResponse = sunnyWeatherNetwork.searchPlaces(query);
                if (placeResponse.getStatus().equals("ok")) {//如果状态ok了，一般来说places就不会有空指针异常
                    places = placeResponse.getPlaces();// 获取到包含地区信息的list
                    Log.d("Repository", "place response success ");
                    placesData.postValue(places);//将list传入Livedata内，并准备返回
                } else {
                    //返回状态不是ok的情况
                    Log.d("Repository", "place status is" + placeResponse.getStatus());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Repository", "PlaceResponse Error!");
            } finally {
                Log.d("Repository", "PlaceResponse finish!");
            }

        }).start();
        return placesData;//返回livedata
    }

    //这里属于显示天气信息模块中的刷新天气时用到的方法
    public MutableLiveData<Weather> refreshWeather(String lng, String lat) {
        MutableLiveData<Weather> weatherData = new MutableLiveData<>();
        Log.d("WeatherViewModel", "refresh Weather 数据申请中" + lng + "---" + lat);
        new Thread(() -> {
            try {
                RealtimeResponse realtimeResponse;
                DailyResponse dailyResponse;
                do {
                    realtimeResponse = sunnyWeatherNetwork.getRealtimeWeather(lng, lat);
                    dailyResponse = sunnyWeatherNetwork.getDailyWeather(lng, lat);
                    Log.d("Repository", "refresh Weather 数据申请中");
                    sleep(500);
                } while (realtimeResponse == null || dailyResponse == null);

                if (realtimeResponse.getStatus().equals("ok") && dailyResponse.getStatus().equals("ok")) {
                    Weather weather = new Weather(realtimeResponse.getResult().getRealtime(),
                            dailyResponse.getResult().getDaily());
                    weatherData.postValue(weather);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return weatherData;
    }

    //物理存储地点信息
    public static void savePlace(PlaceResponse.Place place) {

        PlaceDao.savePlace(place);
    }

    public static PlaceResponse.Place getSavedPlace() {

        return PlaceDao.getSavedPlace();
    }

    public static Boolean isPlaceSaved() {

        return PlaceDao.isPlaceSaved();
    }
}