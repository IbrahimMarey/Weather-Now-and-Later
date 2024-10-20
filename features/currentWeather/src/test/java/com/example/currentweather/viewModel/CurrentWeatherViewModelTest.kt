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
    fun testGetCurrentWeatherFetch() = runBlockingTest {
        // When
        val result = viewModel.cityWeather
        // Then
        assertEquals(UIState.Fetch, result.value)
    }
}