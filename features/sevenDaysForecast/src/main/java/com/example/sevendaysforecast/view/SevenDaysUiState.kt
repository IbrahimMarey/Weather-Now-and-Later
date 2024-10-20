package com.example.sevendaysforecast.view

import com.example.domin.currentWeather.entities.DailyForecastDomainEntity

data class SevenDaysUiState(
    val dailyForecasts: List<DailyForecastDomainEntity> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
    )

sealed class SevenDaysForecastIntent {
    data object LoadForecast : SevenDaysForecastIntent()
}