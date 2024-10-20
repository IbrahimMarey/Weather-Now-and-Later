package com.example.data.di

import com.example.data.repos.CityInputRepo
import com.example.data.repos.CurrentWeatherWeatherRepo
import com.example.data.repos.SevenDaysForeCastRepo
import com.example.domin.cityInput.repo.ICityInputRepo
import com.example.domin.currentWeather.repo.ICurrentWeatherRepo
import com.example.domin.sevenDaysForecast.repo.ISevenDaysForeCastRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ReposModules {
    @Binds
    abstract fun provideCityInputRepo(cityInputRepo: CityInputRepo): ICityInputRepo

    @Binds
    abstract fun provideCurrentWeatherRepo(currentWeatherRepo: CurrentWeatherWeatherRepo): ICurrentWeatherRepo

    @Binds
    abstract fun provideSevenDaysForecastRepo(sevenDaysForeCastRepo: SevenDaysForeCastRepo): ISevenDaysForeCastRepo
}