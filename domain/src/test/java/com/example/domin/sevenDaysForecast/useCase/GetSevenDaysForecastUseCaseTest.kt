package com.example.domin.sevenDaysForecast.useCase

import com.example.domin.currentWeather.entities.CityDataEntity
import com.example.domin.currentWeather.entities.DailyForecastDomainEntity
import com.example.domin.currentWeather.entities.TemperatureDomainEntity
import com.example.domin.sevenDaysForecast.repo.ISevenDaysForeCastRepo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetSevenDaysForecastUseCaseTest {
    private lateinit var sevenDaysForeCastRepo: ISevenDaysForeCastRepo
    private lateinit var getSevenDaysForecastUseCase: GetSevenDaysForecastUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        sevenDaysForeCastRepo = mock(ISevenDaysForeCastRepo::class.java)
        getSevenDaysForecastUseCase = GetSevenDaysForecastUseCase(sevenDaysForeCastRepo)
    }

    @Test
    fun testSevenDaysForecastUseCaseToReturnListOfDailyWeather() = runBlockingTest {
        // Given
        val lat = 10.0
        val lng = 20.0
        val cityData = CityDataEntity("city", lat = lat, lng = lng)
        val sevenDaysForecast = listOf(
            DailyForecastDomainEntity(
                date = 1L,
                temperature = TemperatureDomainEntity(min = 1.0, max = 1.0),
                weatherCondition = "condition",
                icon = "icon"
            )
        )

        // Mock
        `when`(sevenDaysForeCastRepo.getCityData()).thenReturn(flow { emit(cityData) })

        `when`(sevenDaysForeCastRepo.getSevenDaysForecastForCity(cityData.lat, cityData.lng))
            .thenReturn(sevenDaysForecast)
        // When
        val result = getSevenDaysForecastUseCase().first() // Collect the first emission from the flow

        // Then
        verify(sevenDaysForeCastRepo).getCityData()
        verify(sevenDaysForeCastRepo).getSevenDaysForecastForCity(cityData.lat, cityData.lng)
        assert(result == sevenDaysForecast) // Assert that the result matches the mock
    }
}