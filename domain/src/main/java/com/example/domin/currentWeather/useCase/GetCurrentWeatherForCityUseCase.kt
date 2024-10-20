package com.example.domin.currentWeather.useCase

import com.example.domin.currentWeather.repo.ICurrentWeatherRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentWeatherForCityUseCase @Inject constructor(private val iCurrentWeatherRepo: ICurrentWeatherRepo) {
    operator fun invoke() = flow{
        iCurrentWeatherRepo.getCityData()
            .collect{
                if (it != null)
                    emit(iCurrentWeatherRepo.getCurrentWeatherForCity(it.lat,it.lng))
                else
                    emit(null)
        }
    }
}