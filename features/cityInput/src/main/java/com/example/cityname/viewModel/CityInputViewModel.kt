package com.example.cityname.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domin.cityInput.useCase.SaveCityNameUseCase
import com.example.domin.currentWeather.entities.CityDataEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityInputViewModel @Inject constructor(private val saveCityNameUseCase: SaveCityNameUseCase): ViewModel() {

    fun insertCity(cityData: CityDataEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            saveCityNameUseCase(cityData).catch {
                        Log.i("TAG", "insertCity: catch $it")
                    }   .collect {
                        Log.i("TAG", "insertCity: collect  ")
                    }
        }
    }
}