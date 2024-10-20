package com.example.data.entities.mappers

import com.example.data.entities.CityInputEntity
import com.example.domin.currentWeather.entities.CityDataEntity

fun CityDataEntity.mapToDataLayer(): CityInputEntity {
    return CityInputEntity(
        name = cityName,
        lat = lat,
        lng = lng
    )
}