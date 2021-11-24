package com.nicechina.weather.network;


import com.nicechina.weather.model.PlaceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface PlaceService {
    @GET("v2/place?token=VKC3aGqblsPQTRQT&lang=en_US")
    Call<PlaceResponse> searchPlace(@Query("query") String query);
}
