package com.example.sevendaysforecast.viewModel

import com.example.domin.currentWeather.entities.DailyForecastDomainEntity
import com.example.domin.currentWeather.entities.TemperatureDomainEntity
import com.example.domin.sevenDaysForecast.useCase.GetSevenDaysForecastUseCase
import com.example.sevendaysforecast.view.SevenDaysForecastIntent
import com.example.sevendaysforecast.view.SevenDaysUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class SevenDaysForecastViewModelTest {
    private lateinit var sevenDaysForecastUseCase: GetSevenDaysForecastUseCase
    private lateinit var viewModel: SevenDaysForecastViewModel

    @Before
    fun setUp() {
        sevenDaysForecastUseCase = mock()
        viewModel = SevenDaysForecastViewModel(sevenDaysForecastUseCase)
    }

    @Test
    fun testFetchSEvenDaysForecast() = runBlockingTest {
        // Given
        val dailyForecasts = listOf(
            DailyForecastDomainEntity(1L, TemperatureDomainEntity(1.0, 1.0), "condition", "icon")
        )

        // Mock
        `when`(sevenDaysForecastUseCase()).thenReturn(flow { emit(dailyForecasts) })

        // When
        viewModel.sendIntent(SevenDaysForecastIntent.LoadForecast)
        val result = viewModel.state

        // Then
        delay(1000)
        assertEquals(
            SevenDaysUiState(
            dailyForecasts = dailyForecasts,
            isLoading = false
        ), result.value)
    }
}