package com.example.cityname.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cityname.utils.getCityDataEntityByLatAndLng
import com.example.cityname.utils.getLatLngByCityName
import com.example.cityname.viewModel.CityInputViewModel
import com.example.core.ui.component.CustomButton
import com.example.core.ui.component.CustomTextField
import com.example.core.ui.theme.WeatherNowAndLaterTheme
import com.example.core.utils.CityNameUtils
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun CityInputView(
    navController: NavController,
    viewModel : CityInputViewModel = hiltViewModel()
) {
    WeatherNowAndLaterTheme {
        var searchedCity by remember { mutableStateOf("") }
        var markerPosition by remember { mutableStateOf(LatLng(37.7749, -122.4194)) }
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(markerPosition, 10f) // San Francisco
        }

        val ctx = LocalContext.current
        Box {
            val marker = MarkerState(position = markerPosition)
            val keyboardController = LocalSoftwareKeyboardController.current
            CustomTextField(
                value = searchedCity,
                onValueChange = {searchedCity = it },
                onSearchSubmit = {
                    val location = getLatLngByCityName(ctx, searchedCity)
                    if ( location != null)
                    {
                        markerPosition = location
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(location,10f)
                        searchedCity = ""
                        keyboardController?.hide()
                    }else {
                        Toast.makeText(ctx, "Not Valid City Name", Toast.LENGTH_SHORT).show()
                    }
                },
                placeholder = "Search...")


            GoogleMap(
                modifier = Modifier
                    .fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapClick = {
                    markerPosition = it
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(it,10f)

                }
            ) {
                Marker(state = marker, title = CityNameUtils.getAddress(context = ctx, marker.position.latitude, marker.position.longitude))
            }

            CustomButton(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    viewModel.insertCity(getCityDataEntityByLatAndLng(marker.position.latitude,marker.position.longitude,ctx))
                    navController.navigateUp()
            },
                title = "Save City"
            )
        }
    }
}