package ua.sviatkuzbyt.cityweather.ui.elements.other

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace20
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace8

@Composable
fun SpacerMedium(){
    Spacer(Modifier.height(sizeSpace16))
}

@Composable
fun SpacerSmall(){
    Spacer(Modifier.height(sizeSpace8))
}

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

val shapeContainer = RoundedCornerShape(sizeSpace20)