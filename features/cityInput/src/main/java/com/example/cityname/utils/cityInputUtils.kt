package com.example.cityname.utils

import android.content.Context
import android.location.Geocoder
import android.location.Location
import com.example.core.utils.CityNameUtils
import com.example.domin.currentWeather.entities.CityDataEntity
import com.google.android.gms.maps.model.LatLng
import java.util.Locale
import kotlin.math.ln

fun getLatLngByCityName(context: Context, cityName: String): LatLng? {
    val geocoder = Geocoder(context, Locale.getDefault())
    return try {
        val addresses = geocoder.getFromLocationName(cityName, 1)
        if (!addresses.isNullOrEmpty()) {
            val location = addresses[0]
            LatLng(location.latitude,location.longitude)
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}

fun getCityDataEntityByLatAndLng(lat: Double, lng: Double, ctx: Context): CityDataEntity {
    val cityName = CityNameUtils.getAddress(context = ctx, lat, lng)
    return CityDataEntity(cityName,lat,lng)
}