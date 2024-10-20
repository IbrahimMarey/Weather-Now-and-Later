package com.example.domin.sevenDaysForecast.repo

import com.example.domin.currentWeather.entities.CityDataEntity
import com.example.domin.currentWeather.entities.DailyForecastDomainEntity
import kotlinx.coroutines.flow.Flow

interface ISevenDaysForeCastRepo {
    fun getCityData(): Flow<CityDataEntity>
    suspend fun getSevenDaysForecastForCity(lat: Double, lng: Double): List<DailyForecastDomainEntity>
}