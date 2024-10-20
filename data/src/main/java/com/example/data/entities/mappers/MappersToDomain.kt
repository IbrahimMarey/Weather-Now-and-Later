package com.example.data.entities.mappers

import com.example.data.entities.CityInputEntity
import com.example.data.entities.Current
import com.example.data.entities.Daily
import com.example.data.entities.WeatherDataEntity
import com.example.domin.currentWeather.entities.CityDataEntity
import com.example.domin.currentWeather.entities.CurrentWeatherDomainEntity
import com.example.domin.currentWeather.entities.DailyForecastDomainEntity
import com.example.domin.currentWeather.entities.TemperatureDomainEntity
import com.example.domin.currentWeather.entities.WeatherDomainEntity

fun CityInputEntity.mapToDomainEntity(): CityDataEntity {
    return CityDataEntity(
        cityName = name,
        lat= lat,
        lng = lng
    )
}

fun WeatherDataEntity.toDomain(): WeatherDomainEntity {
    return WeatherDomainEntity(
        current = current.toDomain(),
        dailyForecasts = daily.map { it.toDomain() } ,//?: emptyList(),
        lat = lat,
        long = lon,
    )
}

fun Current.toDomain(): CurrentWeatherDomainEntity {
    return CurrentWeatherDomainEntity(
        temperature = temp ,
        condition = weather.firstOrNull()?.description ?: "Unknown",
        icon = weather.firstOrNull()?.icon ?: "",
        dt = dt,
        clouds = clouds,
        humidity = humidity,
        windSpeed = wind_speed.toInt(),
        visibility =visibility

    )
}

fun Daily.toDomain(): DailyForecastDomainEntity {
    return DailyForecastDomainEntity(
        date = dt ,
        temperature = TemperatureDomainEntity(
            min = temp.min as? Double ?: 0.0,
            max = temp.max as? Double ?: 0.0
        ),
        weatherCondition = weather.firstOrNull()?.description ?: "Unknown",
        icon = weather.firstOrNull()?.icon ?: ""
    )
}