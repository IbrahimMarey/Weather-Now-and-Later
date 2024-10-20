package com.example.data.repos

import com.example.data.dataSource.ILocalDataSource
import com.example.data.dataSource.IRemoteDataSource
import com.example.data.entities.mappers.mapToDomainEntity
import com.example.data.entities.mappers.toDomain
import com.example.domin.currentWeather.entities.DailyForecastDomainEntity
import com.example.domin.sevenDaysForecast.repo.ISevenDaysForeCastRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SevenDaysForeCastRepo @Inject constructor(private val iLocalDataSource: ILocalDataSource, private val iRemoteDataSource: IRemoteDataSource) : ISevenDaysForeCastRepo {
    override fun getCityData() = flow {
        if (iLocalDataSource.getCities().isNotEmpty())
            emit(iLocalDataSource.getCities()[0].mapToDomainEntity())
    }

    override suspend fun getSevenDaysForecastForCity(
        lat: Double,
        lng: Double
    ): List<DailyForecastDomainEntity> {
        return iRemoteDataSource.getCurrentWeather(lat,lng).toDomain().dailyForecasts
    }
}