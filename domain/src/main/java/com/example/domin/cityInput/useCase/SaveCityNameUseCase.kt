package com.example.domin.cityInput.useCase

import com.example.domin.cityInput.repo.ICityInputRepo
import com.example.domin.currentWeather.entities.CityDataEntity
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveCityNameUseCase  @Inject constructor(private val cityRepo: ICityInputRepo) {
    operator fun invoke(cityData: CityDataEntity) = flow{
        cityRepo.deleteAllCities().collect{
            emit(cityRepo.insertCityInput(cityData))
        }
    }
}