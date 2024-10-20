package com.example.data.repos

import com.example.data.dataSource.ILocalDataSource
import com.example.data.entities.mappers.mapToDataLayer
import com.example.domin.currentWeather.entities.CityDataEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class CityInputRepoTest{
    private val localDataSource: ILocalDataSource = mock(ILocalDataSource::class.java)
    private val cityInputRepo: CityInputRepo = CityInputRepo(localDataSource)

    @Test
    fun testInsertCityInput() = runBlocking {
        // Given
        val cityDataEntity = CityDataEntity("Sample City",10.0,20.0)
        // When
        cityInputRepo.insertCityInput(cityDataEntity)

        // Then
        verify(localDataSource).insertCityInput(cityDataEntity.mapToDataLayer())
    }

    @Test
    fun testDeleteAllCities() = runBlocking {
        // Given
        `when`(localDataSource.deleteAllCities()).thenReturn(Unit)

        // When
        val result = cityInputRepo.deleteAllCities().first()
        // Then
        assertEquals(Unit, result)
        verify(localDataSource).deleteAllCities()
    }
}