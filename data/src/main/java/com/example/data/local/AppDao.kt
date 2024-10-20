package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entities.CityInputEntity

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: CityInputEntity)

    @Query("SELECT * FROM city")
    fun getCity(): List<CityInputEntity>

    @Query("DELETE FROM city")
    fun deleteAllCities()
}