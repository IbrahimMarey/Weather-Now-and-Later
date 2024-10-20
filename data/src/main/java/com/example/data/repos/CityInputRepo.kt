package com.example.data.repos

import com.example.data.dataSource.ILocalDataSource
import com.example.data.entities.CityInputEntity
import com.example.data.entities.mappers.mapToDataLayer
import com.example.domin.cityInput.repo.ICityInputRepo
import com.example.domin.currentWeather.entities.CityDataEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CityInputRepo @Inject constructor(private val localDataSource: ILocalDataSource):
    ICityInputRepo {
    override suspend fun insertCityInput(cityDataEntity: CityDataEntity){
        localDataSource.insertCityInput(cityDataEntity.mapToDataLayer())
    }

    override fun deleteAllCities()= flow{
        emit(localDataSource.deleteAllCities())
    }
}