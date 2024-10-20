package com.example.data.repos

import com.example.data.dataSource.ILocalDataSource
import com.example.data.dataSource.IRemoteDataSource
import com.example.data.entities.CityInputEntity
import com.example.data.entities.Current
import com.example.data.entities.Daily
import com.example.data.entities.FeelsLike
import com.example.data.entities.Temp
import com.example.data.entities.WeatherDataEntity
import com.example.data.entities.mappers.mapToDomainEntity
import com.example.data.entities.mappers.toDomain
import com.example.domin.sevenDaysForecast.repo.ISevenDaysForeCastRepo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class SevenDaysForeCastRepoTest{
    private lateinit var localDataSource: ILocalDataSource
    private lateinit var remoteDataSource: IRemoteDataSource
    private lateinit var sevenDaysForeCastRepo : ISevenDaysForeCastRepo

    @Before
    fun setUP() {
        localDataSource = mock(ILocalDataSource::class.java)
        remoteDataSource = mock(IRemoteDataSource::class.java)
        sevenDaysForeCastRepo = SevenDaysForeCastRepo(localDataSource, remoteDataSource)
    }
    @Test
    fun testGetCityData_withCities(): Unit = runBlocking {
        // Given
        val cityInputEntity = CityInputEntity("city",10.0,20.0)
        val domainEntity = cityInputEntity.mapToDomainEntity()
        `when`(localDataSource.getCities()).thenReturn(listOf(cityInputEntity))
        // When
        val result = sevenDaysForeCastRepo.getCityData().first()
        // Then
        assertEquals(domainEntity, result)
    }

    @Test
    fun testGetCityData_noCities(): Unit = runBlocking {
        // Given
        `when`(localDataSource.getCities()).thenReturn(listOf(CityInputEntity("city",10.0,20.0)))

        // When
        val result = sevenDaysForeCastRepo.getCityData().first()
        // Then
        assertTrue(result.cityName == "city" )
    }

    @Test
    fun testGetSevenDaysForecastForCity(): Unit = runBlocking {
        // Given
        val lat = 10.0
        val lng = 20.0
        val weatherDataEntity = WeatherDataEntity(
            current = Current(1,2.0,1,2.0,1,1,1,1,1.0,1.0,1, emptyList(),1,1.0),
            daily = listOf(
                Daily(
                    clouds = 20,
                    dew_point = 15.0,
                    dt = 1634617200,
                    feels_like = FeelsLike(1.0,1.0,1.0,1.0),
                    humidity = 60,
                    pop = 0.1,
                    pressure = 1012,
                    temp = Temp(1.0,1.0,1.0,1.0,1.0,1.0),
                    uvi = 5.0,
                    weather = emptyList(),
                    wind_deg = 180.0,
                    wind_speed = 5.0,
                    sunset = 1,
                    summary = "",
                    sunrise = 1,
                    moonrise = 1,
                    moonset = 1,
                    wind_gust = 1.0,
                    moon_phase = 1.0
                )
            ),
            hourly = emptyList(),
            lat = 35.0,
            lon = 139.0,
            minutely = emptyList(),
            timezone_offset = 1,
            timezone = ""
        )

        // Mock the behavior of the remoteDataSource
        `when`(remoteDataSource.getCurrentWeather(lat, lng)).thenReturn(weatherDataEntity)

        // When
        val result = sevenDaysForeCastRepo.getSevenDaysForecastForCity(lat, lng)

        // Then
        assertEquals(weatherDataEntity.toDomain().dailyForecasts, result)
    }
}