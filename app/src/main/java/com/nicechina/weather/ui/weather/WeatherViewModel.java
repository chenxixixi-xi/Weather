package com.nicechina.weather.ui.weather;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.nicechina.weather.model.PlaceResponse;
import com.nicechina.weather.model.Weather;
import com.nicechina.weather.network.Repository;

public class WeatherViewModel extends ViewModel {

    private static MutableLiveData<PlaceResponse.Location> locationLiveData =
            new MutableLiveData<>();
    public String locationLng = "";
    public String locationLat = "";
    public String placeName = "";
    final static Repository repository = new Repository();

    public static final LiveData<Weather> weatherLiveData = Transformations.switchMap(locationLiveData,
            location -> repository.refreshWeather(location.getLng(), location.getLat()));


    public void refreshWeather(String lng, String lat) {
        Log.d("WeatherViewModel", "lng is " + lng);
        locationLiveData.postValue(new PlaceResponse.Location(lng, lat));
    }
}
