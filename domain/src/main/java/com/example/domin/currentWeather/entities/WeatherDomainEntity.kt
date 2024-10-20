package com.example.domin.currentWeather.entities

data class WeatherDomainEntity (
    val current: CurrentWeatherDomainEntity?,
    val dailyForecasts: List<DailyForecastDomainEntity>,
//    val alerts: List<AlertDomainModel>,
    val lat: Double?, val long: Double?
)

data class CurrentWeatherDomainEntity(
    val temperature: Double,
    val condition: String,
    val icon: String,
    val dt: Int?,
    val clouds: Int?,
    val humidity: Int?,
    val windSpeed: Int?,
    val visibility: Int?
)

data class DailyForecastDomainEntity(
    val date: Long,
    val temperature: TemperatureDomainEntity,
    val weatherCondition: String,
    val icon: String
)

data class TemperatureDomainEntity(
    val min: Double,
    val max: Double
)