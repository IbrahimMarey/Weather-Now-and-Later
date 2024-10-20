package com.example.data.dataSource

import com.example.data.entities.CityInputEntity
import com.example.data.local.AppDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: AppDao): ILocalDataSource {
    override suspend fun insertCityInput(cityInputEntity: CityInputEntity) {
        dao.insertCity(cityInputEntity)
    }

    override suspend fun getCities(): List<CityInputEntity> {
        return dao.getCity()
    }

    override suspend fun deleteAllCities() {
        dao.deleteAllCities()
    }
}