package com.example.currentweather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ui.UIState
import com.example.domin.currentWeather.entities.WeatherDomainEntity
import com.example.domin.currentWeather.useCase.GetCurrentWeatherForCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(private val currentWeatherForCityUseCase: GetCurrentWeatherForCityUseCase): ViewModel() {

    private val _cityWeather: MutableStateFlow<UIState<WeatherDomainEntity>> = MutableStateFlow(
        UIState.Fetch)
    val cityWeather : StateFlow<UIState<WeatherDomainEntity>> = _cityWeather

    fun getCityCurrentWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            currentWeatherForCityUseCase()
                .catch {
                    _cityWeather.value = UIState.Error(it.message?: "Not Found Data")
                }
                .collect{
                    if(it != null)
                        _cityWeather.value = UIState.Success(it)
                    else
                        _cityWeather.value = UIState.Error("Please Search for a City")
                }
        }
    }
}