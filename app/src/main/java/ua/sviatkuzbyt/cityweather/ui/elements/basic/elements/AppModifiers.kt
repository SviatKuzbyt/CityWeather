package ua.sviatkuzbyt.cityweather.ui.elements.basic.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.basicClick(onClick: () -> Unit) = clickable(
    interactionSource = null,
    indication = null,
    onClick = onClick
)

@Composable
fun Modifier.containerBackground() = background(
    shape = shapeContainer,
    color = MaterialTheme.colorScheme.surfaceContainer
)