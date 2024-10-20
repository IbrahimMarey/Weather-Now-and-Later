package com.example.domin.currentWeather.repo

import com.example.domin.currentWeather.entities.CityDataEntity
import com.example.domin.currentWeather.entities.WeatherDomainEntity
import kotlinx.coroutines.flow.Flow

interface ICurrentWeatherRepo {
    fun getCityData(): Flow<CityDataEntity?>
    suspend fun getCurrentWeatherForCity(lat: Double, lng: Double): WeatherDomainEntity
}