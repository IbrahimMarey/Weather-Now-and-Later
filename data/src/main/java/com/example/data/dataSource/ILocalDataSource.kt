package com.example.data.dataSource

import com.example.data.entities.CityInputEntity

interface ILocalDataSource {
    suspend fun insertCityInput(cityInputEntity: CityInputEntity)
    suspend fun getCities(): List<CityInputEntity>
    suspend fun deleteAllCities()
}