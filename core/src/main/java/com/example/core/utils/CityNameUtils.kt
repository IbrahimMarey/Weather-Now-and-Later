package com.example.core.utils

import android.content.Context
import android.location.Geocoder
import android.location.Location
import java.io.IOException

object CityNameUtils {
    fun getAddress(context: Context, lat:Double, lng:Double): String {
        val geocoder = Geocoder(context)
        try {
            val list = geocoder.getFromLocation(lat, lng, 1)
            if (!list.isNullOrEmpty()) {
                return list[0].adminArea
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return "Unknown"
    }
}