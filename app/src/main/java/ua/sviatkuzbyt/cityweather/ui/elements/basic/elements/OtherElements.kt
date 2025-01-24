package ua.sviatkuzbyt.cityweather.ui.elements.basic.elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
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

val shapeContainer = RoundedCornerShape(sizeSpace20)