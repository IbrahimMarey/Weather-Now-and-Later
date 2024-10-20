package com.example.data.di

import com.example.data.dataSource.ILocalDataSource
import com.example.data.dataSource.IRemoteDataSource
import com.example.data.dataSource.LocalDataSource
import com.example.data.dataSource.RemoteDataSource
import com.example.data.remote.WeatherApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesProviders {
    @Binds
    abstract fun provideLocalDataSource(localDataSource: LocalDataSource): ILocalDataSource
    @Binds
    abstract fun provideRemoteDataSource(remoteDataSource: RemoteDataSource) : IRemoteDataSource
}