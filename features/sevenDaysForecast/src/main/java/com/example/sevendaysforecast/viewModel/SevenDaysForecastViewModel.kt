package com.example.sevendaysforecast.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ui.UIState
import com.example.domin.currentWeather.entities.DailyForecastDomainEntity
import com.example.domin.sevenDaysForecast.useCase.GetSevenDaysForecastUseCase
import com.example.sevendaysforecast.view.SevenDaysForecastIntent
import com.example.sevendaysforecast.view.SevenDaysUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SevenDaysForecastViewModel @Inject constructor(private val sevenDaysForecastUseCase: GetSevenDaysForecastUseCase) : ViewModel() {
    private val _state = MutableStateFlow(SevenDaysUiState())
    val state: StateFlow<SevenDaysUiState> = _state.asStateFlow()

    init {
        sendIntent(SevenDaysForecastIntent.LoadForecast)
    }

    fun sendIntent(intent: SevenDaysForecastIntent) {
        when (intent) {
            is SevenDaysForecastIntent.LoadForecast -> loadSevenDaysForecast()
        }
    }

    private fun loadSevenDaysForecast() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = SevenDaysUiState(isLoading = true)
            sevenDaysForecastUseCase()
                .catch { error ->
                    _state.value = SevenDaysUiState(
                        isLoading = false,
                        errorMessage = error.message ?: "Not Found Data"
                    )
                }
                .collect { forecasts ->
                    _state.value = SevenDaysUiState(
                        dailyForecasts = forecasts,
                        isLoading = false
                    )
                }
        }
    }
}