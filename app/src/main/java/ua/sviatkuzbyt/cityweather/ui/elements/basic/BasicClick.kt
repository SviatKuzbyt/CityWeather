package ua.sviatkuzbyt.cityweather.ui.elements.basic

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

fun Modifier.basicClick(onClick: () -> Unit) = clickable(
    interactionSource = null,
    indication = null,
    onClick = onClick
)