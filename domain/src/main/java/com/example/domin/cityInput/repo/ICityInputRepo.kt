package com.example.domin.cityInput.repo

import com.example.domin.currentWeather.entities.CityDataEntity
import kotlinx.coroutines.flow.Flow

interface ICityInputRepo {
    suspend fun insertCityInput(cityDataEntity: CityDataEntity)
    fun deleteAllCities():Flow<Unit>
}