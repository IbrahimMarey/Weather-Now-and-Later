package com.example.features.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cityname.view.CityInputView
import com.example.core.ui.theme.WeatherNowAndLaterTheme
import com.example.currentweather.view.CurrentWeatherView
import com.example.sevendaysforecast.view.SevenDaysForecastView

sealed class Navigation(val route: String) {
    data object CityInput: Navigation(Constants.CITY_INPUT)
    data object CurrentWeather: Navigation(Constants.CURRENT_WEATHER)
    data object SevenDaysForecast: Navigation(Constants.SEVEN_DAYS_FORECAST)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherNavigationHost() {
    WeatherNowAndLaterTheme {
        val navController = rememberNavController()
        val isCityInputView = remember {
            mutableStateOf(true)
        }
        val isSevenDaysForecastView = remember {
            mutableStateOf(true)
        }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                if (!isCityInputView.value)
                    TopAppBar(
                title = { Text("Weather now and later") },
                actions = {
                    if (!isSevenDaysForecastView.value)
                        IconButton(onClick = {
                        isCityInputView.value =true
                        navController.navigate(Navigation.CityInput.route)
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
                }
            ) }

        ) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = Navigation.CurrentWeather.route
            ) {

                composable(Navigation.CurrentWeather.route) {
                    isCityInputView.value =false
                    isSevenDaysForecastView.value = false
                    CurrentWeatherView(onNavigateClick = {navController.navigate(Navigation.SevenDaysForecast.route)})
                }

                composable(Navigation.CityInput.route) {
                    isCityInputView.value =true
                    isSevenDaysForecastView.value = false
                    CityInputView(navController = navController)
                }

                composable(Navigation.SevenDaysForecast.route) {
                    isCityInputView.value =false
                    isSevenDaysForecastView.value = true
                    SevenDaysForecastView()
                }

            }
        }
    }
}