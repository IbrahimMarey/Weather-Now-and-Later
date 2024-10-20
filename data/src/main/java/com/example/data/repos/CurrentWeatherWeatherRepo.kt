package com.example.data.repos

import com.example.data.dataSource.ILocalDataSource
import com.example.data.dataSource.IRemoteDataSource
import com.example.data.entities.mappers.mapToDomainEntity
import com.example.data.entities.mappers.toDomain
import com.example.domin.currentWeather.entities.CityDataEntity
import com.example.domin.currentWeather.entities.WeatherDomainEntity
import com.example.domin.currentWeather.repo.ICurrentWeatherRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentWeatherWeatherRepo @Inject constructor(private val iLocalDataSource: ILocalDataSource, private val iRemoteDataSource: IRemoteDataSource):
    ICurrentWeatherRepo {
    override fun getCityData()= flow {
        if (iLocalDataSource.getCities().isNotEmpty())
            emit(iLocalDataSource.getCities()[0].mapToDomainEntity())
        else
            emit(null)
    }

    override suspend fun getCurrentWeatherForCity(lat: Double, lng: Double): WeatherDomainEntity {
        return iRemoteDataSource.getCurrentWeather(lat,lng).toDomain()
    }
}