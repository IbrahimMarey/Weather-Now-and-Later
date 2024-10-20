package com.example.domin.cityInput.useCase

import com.example.domin.cityInput.repo.ICityInputRepo
import com.example.domin.currentWeather.entities.CityDataEntity
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SaveCityNameUseCaseTest {

    @Mock
    private lateinit var cityRepo: ICityInputRepo

    private lateinit var saveCityNameUseCase: SaveCityNameUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        saveCityNameUseCase = SaveCityNameUseCase(cityRepo)
    }

    @Test
    fun testThatdeleteAllCity_insertNewCity_toBeOnlyOneCityInserted() = runBlockingTest {
        // Given
        val cityData = CityDataEntity("city",1.0,1.0)

        // Mock the repository flows
        `when`(cityRepo.deleteAllCities()).thenReturn(flow { emit(Unit) })
        `when`(cityRepo.insertCityInput(cityData)).thenAnswer {
            flow { emit(Unit) }
        }
        // When
        val result = saveCityNameUseCase(cityData).toList()

        // Then
        verify(cityRepo).deleteAllCities()
        verify(cityRepo).insertCityInput(cityData)
        assert(result.isNotEmpty())
    }
}