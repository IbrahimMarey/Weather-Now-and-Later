package com.example.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.entities.CityInputEntity
import com.example.data.local.AppDao
import com.example.data.local.AppDatabase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var appDao: AppDao

    @Before
    fun setUp() {
        // Create an database
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()

        appDao = database.appDao()
    }

    @After
    fun tearDown() {
        // Close the database after the tests
        database.close()
    }

    @Test
    fun testInsertCity() {
        // Given
        val city = CityInputEntity(name = "Sample City",10.0,20.0)

        // When
        appDao.insertCity(city)

        // Then
        val cities = appDao.getCity()
        assertEquals(1, cities.size)
        assertEquals(city.name, cities[0].name)
    }

    @Test
    fun testDeleteAllCities() {
        // Given
        val city1 = CityInputEntity(name = "City One",1.0,1.0)
        val city2 = CityInputEntity(name = "City Two",2.0,2.0)
        appDao.insertCity(city1)
        appDao.insertCity(city2)

        // When
        appDao.deleteAllCities()

        // Then
        val cities = appDao.getCity()
        assertEquals(0, cities.size)
    }

    @Test
    fun testGetCityWhenNoCities() {
        // When
        val cities = appDao.getCity()

        // Then
        assertEquals(0, cities.size) // Ensure the list is empty
    }
}