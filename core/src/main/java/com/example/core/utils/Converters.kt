package com.example.core.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

object Converters {
    @SuppressLint("SimpleDateFormat")
    fun dateConverter(dt: Long):String {
        val date= Date(dt.times(1000L))
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(date)
    }

    fun convertDateToDay(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, formatter)
        val dayOfWeek: DayOfWeek = date.dayOfWeek
        return capitalizeFirstLetter(dayOfWeek.name)
    }

    internal fun capitalizeFirstLetter(input: String): String {
        if (input.isEmpty()) return input
        return input.substring(0, 1).uppercase() + input.substring(1).lowercase()
    }
}