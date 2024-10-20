package com.example.data.repos

import com.example.data.dataSource.ILocalDataSource
import com.example.data.dataSource.IRemoteDataSource
import com.example.data.entities.Current
import com.example.data.entities.WeatherDataEntity
import com.example.data.entities.mappers.toDomain
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class CurrentWeatherWeatherRepoTest{

    private val localDataSource: ILocalDataSource = mock(ILocalDataSource::class.java)
    private val remoteDataSource: IRemoteDataSource = mock(IRemoteDataSource::class.java)
    private val currentWeatherRepo = CurrentWeatherWeatherRepo(localDataSource, remoteDataSource)

    @Test
    fun testGetCityData_noCities(): Unit = runBlocking {
        // Given
        `when`(localDataSource.getCities()).thenReturn(emptyList())
        // When
        val result = currentWeatherRepo.getCityData().first()
        // Then
        assertNull(result)
        verify(localDataSource).getCities()
    }

    @Test
    fun testGetCurrentWeatherForCity(): Unit = runBlocking {
        // Given
        val lat = 35.0
        val lng = 139.0
        val weatherDataEntity = WeatherDataEntity(
            current = Current(1,2.0,1,2.0,1,1,1,1,1.0,1.0,1, emptyList(),1,1.0),
            daily = emptyList(),
            hourly = emptyList(),
            lat = 35.0,
            lon = 139.0,
            minutely = emptyList(),
            timezone_offset = 1,
            timezone = ""
        )
        val domainWeatherEntity = weatherDataEntity.toDomain() // Convert to domain entity
        `when`(remoteDataSource.getCurrentWeather(lat, lng)).thenReturn(weatherDataEntity)

        // When
        val result = currentWeatherRepo.getCurrentWeatherForCity(lat, lng)

        // Then
        assertEquals(domainWeatherEntity, result)
        verify(remoteDataSource).getCurrentWeather(lat, lng)
    }
}