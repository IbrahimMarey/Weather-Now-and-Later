package com.example.cityname.viewModel

import com.example.domin.cityInput.useCase.SaveCityNameUseCase
import com.example.domin.currentWeather.entities.CityDataEntity
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class CityInputViewModelTest {

    private lateinit var saveCityNameUseCase: SaveCityNameUseCase
    private lateinit var viewModel: CityInputViewModel

    @Before
    fun setUp() {
        saveCityNameUseCase = mock()
        viewModel = CityInputViewModel(saveCityNameUseCase)
    }

    @Test
    fun `insertCity should invoke saveCityNameUseCase and log collect`() = runBlockingTest {
        // Given
        val cityData = CityDataEntity("city", lat = 10.0, lng = 20.0)

        // Mock
        `when`(saveCityNameUseCase(cityData)).thenReturn(flow { emit(Unit) })

        // When
        viewModel.insertCity(cityData)

        // Than
        verify(saveCityNameUseCase).invoke(cityData) // Verify that the use case was invoked
    }
}