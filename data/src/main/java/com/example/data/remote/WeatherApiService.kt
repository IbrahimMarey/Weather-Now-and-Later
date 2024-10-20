package com.example.data.remote

import com.example.data.entities.WeatherDataEntity
import com.example.data.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
       @GET("onecall?")
       suspend fun getWeatherForCity(
           @Query("lat") lat :String,
           @Query("lon") lng:String,
           @Query("appid")appID:String = Constants.API_KEY,
           @Query("exclude") exclude:String = Constants.EXCLUDE,
           @Query("lang") lang:String = Constants.LANG,
           @Query("units") units:String = Constants.UNITS): WeatherDataEntity
}