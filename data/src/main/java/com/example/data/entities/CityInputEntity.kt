package com.example.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityInputEntity(
    val name: String,
    val lat: Double,
    val lng: Double,
    @PrimaryKey val key: String = "$lat:$lng"
)
