package com.example.data.di

import com.example.core.network.RetrofitModule
import com.example.data.remote.WeatherApiService
import com.example.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object WeatherApiServiceModule {
    @Provides
    @RetrofitModule.ApplicationBaseUrl
    fun provideBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }
}