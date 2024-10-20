package com.example.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.ui.theme.CustomBlue
import com.example.core.ui.theme.CustomRed
import com.example.core.ui.theme.padding_24dp
import com.example.core.ui.theme.padding_2dp
import com.example.core.ui.theme.padding_4dp
import com.example.core.ui.theme.size_128

/**
 * [CustomWeatherErrorHandlingComponent] this is component build to generalize error handing in the app
 *
 * @param onClick the component accept onClick that will add a simple view under error massage and IconButton to refresh or to perform action
 */
@Composable
fun CustomWeatherErrorHandlingComponent(
    modifier: Modifier = Modifier,
    onClick: ()-> Unit = {},
    errorMassage: String
) {
    Column(
        modifier = modifier.padding(horizontal = padding_24dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.padding(padding_4dp).size(size_128),
            imageVector = Icons.Outlined.Info,
            contentDescription = "icon",
            tint = CustomRed
        )
        CustomText(text = errorMassage)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CustomText(text = "Reload", color = CustomBlue)
            IconButton(onClick = onClick) {
                Icon(
                    modifier = Modifier.padding(vertical = padding_2dp),
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = "icon",
                    tint = CustomBlue
                )
            }

        }
    }

}
    @Composable
    fun CustomWeatherErrorHandlingComponent(
        modifier: Modifier = Modifier,
        errorMassage: String
    ) {
        Column(
            modifier = modifier.padding(horizontal = padding_24dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.padding(padding_4dp).size(size_128),
                imageVector = Icons.Outlined.Info,
                contentDescription = "icon",
                tint = CustomRed
            )
            CustomText(text = errorMassage)
        }
    }