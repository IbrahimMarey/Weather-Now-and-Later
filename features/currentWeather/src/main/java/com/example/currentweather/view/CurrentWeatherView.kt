package com.example.currentweather.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.ui.theme.WeatherNowAndLaterTheme
import com.example.currentweather.viewModel.CurrentWeatherViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.core.ui.UIState
import com.example.core.ui.component.CustomButton
import com.example.core.ui.component.CustomText
import com.example.core.ui.component.CustomWeatherErrorHandlingComponent
import com.example.core.ui.component.CustomWeatherLoadingComponent
import com.example.core.ui.theme.CustomBlue
import com.example.core.ui.theme.corner_16dp
import com.example.core.ui.theme.fontSize_128sp
import com.example.core.ui.theme.fontSize_18sp
import com.example.core.ui.theme.fontSize_20sp
import com.example.core.ui.theme.fontSize_32sp
import com.example.core.ui.theme.fontSize_42sp
import com.example.core.ui.theme.height_16dp
import com.example.core.ui.theme.height_1dp
import com.example.core.ui.theme.height_32dp
import com.example.core.ui.theme.height_8dp
import com.example.core.ui.theme.padding_16dp
import com.example.core.ui.theme.padding_32dp
import com.example.core.ui.theme.padding_8dp
import com.example.core.ui.theme.width_128dp
import com.example.core.utils.CityNameUtils
import com.example.currentweather.R
import com.example.domin.currentWeather.entities.WeatherDomainEntity


@Composable
fun CurrentWeatherView(
    viewModel: CurrentWeatherViewModel = hiltViewModel(),
    onNavigateClick: ()->Unit
    )
{
    WeatherNowAndLaterTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
        val uiState by viewModel.cityWeather.collectAsState()
        viewModel.getCityCurrentWeather()
        when(uiState) {
            is UIState.Fetch -> {
                CustomWeatherLoadingComponent(modifier = Modifier.align(Alignment.Center))
            }

            is UIState.Error -> {
                CustomWeatherErrorHandlingComponent(modifier = Modifier.align(Alignment.Center), errorMassage = (uiState as UIState.Error).message)
            }

            is UIState.Success -> {
                val entity = (uiState as UIState.Success<WeatherDomainEntity>).data
                CurrentWeatherViewBody(weatherEntity = entity)
                CustomButton(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onClick = onNavigateClick,
                    title = "7 Days Forecast"
                )
            }
        }
        }
    }
}

@Composable
private fun CurrentWeatherViewBody(
    modifier: Modifier = Modifier,
    weatherEntity: WeatherDomainEntity
    ) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(padding_16dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        CityNameAndTempView(
            cityName = CityNameUtils.getAddress(LocalContext.current,weatherEntity.lat?:0.0,weatherEntity.long?:0.0),
            temperature = "${weatherEntity.current?.temperature?.toInt()}",
            )
        WeatherDescription(imagePath = "${weatherEntity.current?.icon}", "${weatherEntity.current?.condition}")
        Spacer(modifier = Modifier.height(height_8dp))
        CurrentWeatherInfo(
            clouds = "${weatherEntity.current?.clouds}",
            humidity = "${weatherEntity.current?.humidity}",
            windSpeed = "${weatherEntity.current?.windSpeed}",
            visibility = "${weatherEntity.current?.visibility}")
        Spacer(modifier = Modifier.height(height_16dp))

    }
}
@Composable
private fun CityNameAndTempView(
    cityName : String,
    temperature: String,

) {
    CustomText(text = cityName, fontSize = fontSize_42sp)
    CustomText(text = "$temperature\u00B0C" , fontSize = fontSize_128sp)
}

@Composable
private fun WeatherDescription(
    imagePath:String,
    weatherDescription: String
) {

    val imageUrl = "https://openweathermap.org/img/wn/$imagePath@4x.png"
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Image from the network",
        )
     CustomText(text = weatherDescription, fontSize = fontSize_32sp)
    }
}

@Composable
private fun CurrentWeatherInfo(
    clouds:String,
    humidity:String,
    windSpeed:String,
    visibility:String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(corner_16dp)),
        colors = CardDefaults.cardColors(
            containerColor = CustomBlue
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding_16dp)
                .zIndex(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TwoVerticalText(painter = painterResource(id = R.drawable.clouds),topText = "Clouds", bottomText = clouds)
                TwoVerticalText(painter = painterResource(id = R.drawable.humidity),topText = "Humidity", bottomText = humidity)
            }

            Box (Modifier.padding(vertical = padding_8dp, horizontal = padding_32dp).fillMaxWidth().height(height_1dp).background(Color.White))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TwoVerticalText(painter = painterResource(id = R.drawable.weather), topText = "Wind Speed", bottomText = windSpeed)
                TwoVerticalText(painter = painterResource(id = R.drawable.visibility),topText = "Visibility", bottomText = visibility)
            }
        }
    }
}

@Composable
private fun TwoVerticalText(
    topText:String,
    bottomText: String,
    painter: Painter
) {
    Column(
        modifier =Modifier.width(width_128dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(modifier = Modifier.height(height_32dp), painter = painter, tint = Color.White, contentDescription = "icon")
        CustomText(text = topText, fontSize = fontSize_18sp, color = Color.White)
        CustomText(text = bottomText, fontSize = fontSize_20sp, color = Color.White)
    }
}

