package com.nicechina.weather.network;


import com.nicechina.weather.SunnyWeatherApplication;
import com.nicechina.weather.model.DailyResponse;
import com.nicechina.weather.model.RealtimeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface WeatherService {
    @GET("v2.5/" + SunnyWeatherApplication.TOKEN + "/{lng},{lat}/realtime.json?lang=en_US")
    Call<RealtimeResponse> getRealtimeWeather
            (@Path("lng") String lng,
             @Path("lat") String lat);

    @GET("v2.5/" + SunnyWeatherApplication.TOKEN + "/{lng},{lat}/daily.json?lang=en_US")
    Call<DailyResponse> getDailyWeather
            (@Path("lng") String lng,
             @Path("lat") String lat);

}
