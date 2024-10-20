package com.example.domin.currentWeather.useCase

import com.example.domin.currentWeather.entities.CityDataEntity
import com.example.domin.currentWeather.entities.WeatherDomainEntity
import com.example.domin.currentWeather.repo.ICurrentWeatherRepo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetCurrentWeatherForCityUseCaseTest {

    private lateinit var iCurrentWeatherRepo: ICurrentWeatherRepo
    private lateinit var getCurrentWeatherForCityUseCase: GetCurrentWeatherForCityUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        iCurrentWeatherRepo = mock(ICurrentWeatherRepo::class.java)
        getCurrentWeatherForCityUseCase = GetCurrentWeatherForCityUseCase(iCurrentWeatherRepo)
    }

    @Test
    fun testCurrentWeatherReturnWhenCityDataIsAvailable() = runBlockingTest {
        // Given
        val lat = 10.0
        val lng = 20.0
        val cityData = CityDataEntity("city", lat = lat, lng = lng)
        val weatherData = WeatherDomainEntity(
            current = null,
            dailyForecasts = emptyList(),
            lat = lat,
            long = lng
        )

        // Mock
        `when`(iCurrentWeatherRepo.getCityData()).thenReturn(flow { emit(cityData) })
        `when`(iCurrentWeatherRepo.getCurrentWeatherForCity(cityData.lat, cityData.lng))
            .thenReturn(weatherData)

        // When
        val result = getCurrentWeatherForCityUseCase().first() // Collect the first emitted value

        // Then
        verify(iCurrentWeatherRepo).getCityData()
        verify(iCurrentWeatherRepo).getCurrentWeatherForCity(cityData.lat, cityData.lng)
        assertTrue(result?.lat == lat)
        assert(result == weatherData)
    }

    @Test
    fun testCurrent_GiveNullWeather_WhenNoCitySelected() = runBlockingTest {
        // Mock
        `when`(iCurrentWeatherRepo.getCityData()).thenReturn(flow { emit(null) })

        // When
        val result = getCurrentWeatherForCityUseCase().toList()

        // Then
        verify(iCurrentWeatherRepo).getCityData()
        verify(iCurrentWeatherRepo, never()).getCurrentWeatherForCity(anyDouble(), anyDouble())
        assert(result.isNotEmpty())
        assert(result[0] == null)
    }
}