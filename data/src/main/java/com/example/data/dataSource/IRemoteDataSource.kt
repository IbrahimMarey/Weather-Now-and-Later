package com.example.data.dataSource

import com.example.data.entities.WeatherDataEntity

interface IRemoteDataSource {
    suspend fun getCurrentWeather(lat: Double,lng: Double): WeatherDataEntity
}