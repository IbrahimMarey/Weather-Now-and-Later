package com.example.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import com.example.core.ui.theme.CustomRed
import com.example.core.ui.theme.corner_16dp
import com.example.core.ui.theme.padding_8dp

/**
 * [CustomWeatherLoadingComponent] this is component build to generalize loading in the app
 */
@Composable
fun CustomWeatherLoadingComponent(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.clip(RoundedCornerShape(corner_16dp))
    ) {
        Column(
            modifier = Modifier.padding(padding_8dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = CustomRed, strokeCap = StrokeCap.Round)
            CustomText(text = "Loading...")
        }
    }
}