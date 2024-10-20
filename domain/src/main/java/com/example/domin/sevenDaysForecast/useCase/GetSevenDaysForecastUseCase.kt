package com.example.domin.sevenDaysForecast.useCase

import com.example.domin.sevenDaysForecast.repo.ISevenDaysForeCastRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSevenDaysForecastUseCase @Inject constructor (private val sevenDaysForeCastRope: ISevenDaysForeCastRepo) {
    operator fun invoke() = flow{
        sevenDaysForeCastRope.getCityData().collect{
            emit(sevenDaysForeCastRope.getSevenDaysForecastForCity(it.lat,it.lng))
        }
    }
}