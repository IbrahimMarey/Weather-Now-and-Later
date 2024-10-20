package com.example.data.dataSource

import com.example.data.entities.WeatherDataEntity
import com.example.data.remote.WeatherApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val weatherApiService: WeatherApiService):IRemoteDataSource {
    override suspend fun getCurrentWeather(lat: Double,lng: Double): WeatherDataEntity {
        return weatherApiService.getWeatherForCity(lat = lat.toString(), lng = lng.toString())
    }
}