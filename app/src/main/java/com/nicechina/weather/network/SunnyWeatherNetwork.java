package com.nicechina.weather.network;

import android.util.Log;

import com.nicechina.weather.model.DailyResponse;
import com.nicechina.weather.model.PlaceResponse;
import com.nicechina.weather.model.RealtimeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SunnyWeatherNetwork {
    private static PlaceService placeService = ServiceCreator.PCreate(PlaceService.class);
    private static PlaceResponse Presult = new PlaceResponse();

    public PlaceResponse searchPlaces(String query) {

        placeService.searchPlace(query).enqueue(new Callback<PlaceResponse>() {
            @Override
            public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                PlaceResponse body = response.body();
                if (body != null) {
                    Presult = body;
                }
            }

            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {
                Log.d("SunnyWeatherNetWork", "连接错误");
                t.printStackTrace();
            }
        });
        return Presult;
    }

    //下面是搜索天气模块
    private final WeatherService weatherService = ServiceCreator.WCreate(WeatherService.class);

    private static RealtimeResponse Rresult;
    private static DailyResponse Dresult;
    private static Boolean RFlag = false;//标志位
    private static Boolean DFlag = true;

    public RealtimeResponse getRealtimeWeather(String lng, String lat) {

        weatherService.getRealtimeWeather(lng, lat).enqueue(new Callback<RealtimeResponse>() {
            @Override
            public void onResponse(Call<RealtimeResponse> call, Response<RealtimeResponse> response) {
                if (response.body() != null) {
                    Rresult = response.body();
                    RFlag = true;
                }
            }

            @Override
            public void onFailure(Call<RealtimeResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
        if (RFlag) return Rresult;
        return null;
    }

    //获取未来天气数据
    public DailyResponse getDailyWeather(String lng, String lat) {

        weatherService.getDailyWeather(lng, lat).enqueue(new Callback<DailyResponse>() {
            @Override
            public void onResponse(Call<DailyResponse> call, Response<DailyResponse> response) {
                if (response.body() != null) {
                    Dresult = response.body();
                    DFlag = true;
                }
            }

            @Override
            public void onFailure(Call<DailyResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
        if (DFlag) return Dresult;
        return null;
    }

}