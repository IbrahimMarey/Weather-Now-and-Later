package com.example.currentweather.viewModel

import com.example.core.ui.UIState
import com.example.domin.currentWeather.entities.WeatherDomainEntity
import com.example.domin.currentWeather.useCase.GetCurrentWeatherForCityUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class CurrentWeatherViewModelTest {

    private lateinit var currentWeatherForCityUseCase: GetCurrentWeatherForCityUseCase
    private lateinit var viewModel: CurrentWeatherViewModel

    @Before
    fun setUp() {
        currentWeatherForCityUseCase = mock()
        viewModel = CurrentWeatherViewModel(currentWeatherForCityUseCase)
    }

    @Test
    fun testGetCurrentWeatherWhenSuccessFetch() = runBlockingTest {
        // Given
        val weatherData = WeatherDomainEntity(
            current = null,
            dailyForecasts = emptyList(),
            lat = 10.0,
            long = 20.0
        )
        // Mock
        `when`(currentWeatherForCityUseCase()).thenReturn(flow { emit(weatherData) })
        // When
        viewModel.getCityCurrentWeather()
        val result = viewModel.cityWeather
        delay(1000)
        // Then
        assertEquals(UIState.Success(weatherData), result.value)
    }

    @Test
    fun testGetCurrentWeatherFetch() = runBlockingTest {
        // When
        val result = viewModel.cityWeather
        // Then
        assertEquals(UIState.Fetch, result.value)
    }
}