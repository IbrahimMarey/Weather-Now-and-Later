package com.example.core.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.core.ui.theme.CustomRed
import com.example.core.ui.theme.padding_16dp
import com.example.core.ui.theme.padding_8dp

/**
 * [CustomButton] this is button with some modifiers and bg color and text to give it best behavior for button
 */
@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick:()->Unit,
    title: String,
    bgColor: Color = CustomRed
) {
    Button(
        modifier = modifier.padding(horizontal = padding_16dp, vertical = padding_8dp).fillMaxWidth(),
        colors = ButtonColors(
            containerColor = bgColor,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        ),
        onClick = onClick) {
        CustomText(text = title, color = Color.White)
    }
}