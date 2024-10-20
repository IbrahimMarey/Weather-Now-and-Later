package com.example.sevendaysforecast.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.core.ui.component.CustomText
import com.example.core.ui.component.CustomWeatherErrorHandlingComponent
import com.example.core.ui.component.CustomWeatherLoadingComponent
import com.example.core.ui.theme.CustomBlue
import com.example.core.ui.theme.WeatherNowAndLaterTheme
import com.example.core.ui.theme.corner_16dp
import com.example.core.ui.theme.padding_16dp
import com.example.core.ui.theme.padding_8dp
import com.example.core.utils.Converters
import com.example.core.utils.Converters.convertDateToDay
import com.example.domin.currentWeather.entities.DailyForecastDomainEntity
import com.example.sevendaysforecast.viewModel.SevenDaysForecastViewModel

@Composable
fun SevenDaysForecastView(
    viewModel: SevenDaysForecastViewModel = hiltViewModel()
) {
    WeatherNowAndLaterTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            val uiState by viewModel.state.collectAsState()

            when {
                uiState.isLoading -> {
                    CustomWeatherLoadingComponent(modifier = Modifier.align(Alignment.Center))
                }
                uiState.errorMessage != null -> {
                    CustomWeatherErrorHandlingComponent(
                        modifier = Modifier.align(Alignment.Center),
                        errorMassage = uiState.errorMessage ?: "",
                        onClick = {viewModel.sendIntent(SevenDaysForecastIntent.LoadForecast)}
                    )
                }
                uiState.dailyForecasts.isNotEmpty() -> {
                    SevenDaysForecastViewBody(dailyList = uiState.dailyForecasts)
                }
            }
        }
    }
}

@Composable
private fun SevenDaysForecastViewBody(dailyList: List<DailyForecastDomainEntity>) {
    LazyColumn {
        items(dailyList.size) {
            DayForecastItem(dailyEntity = dailyList[it])
        }
    }
}

@Composable
private fun DayForecastItem(dailyEntity: DailyForecastDomainEntity) {
    Column(
        modifier = Modifier.padding(padding_16dp)
    ) {
        DayAndDate(date = dailyEntity.date)
        DayWeatherInfo(dailyEntity = dailyEntity)
    }
}

@Composable
private fun DayWeatherInfo(dailyEntity: DailyForecastDomainEntity) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = corner_16dp, bottomEnd = corner_16dp))
            .background(color = Color.Gray.copy(alpha = .7f))
    ) {
        val imageUrl = "https://openweathermap.org/img/wn/${dailyEntity.icon}@4x.png"
        AsyncImage(
            model = imageUrl,
            contentDescription = "Image from the network",
        )
        CustomText(text = dailyEntity.weatherCondition, color = Color.White)
        CustomText(text = "${dailyEntity.temperature.min.toInt()}°C : ${dailyEntity.temperature.max.toInt()}°C", color = Color.White)

    }
}

@Composable
private fun DayAndDate(
    date: Long
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = corner_16dp, topEnd = corner_16dp))
            .background(color = CustomBlue)
    ) {
        CustomText(text = convertDateToDay(Converters.dateConverter(date)), color = Color.White, modifier = Modifier.padding(horizontal = padding_8dp))
        CustomText(text = Converters.dateConverter(date), color = Color.White, modifier = Modifier.padding(horizontal = padding_8dp))
    }
}