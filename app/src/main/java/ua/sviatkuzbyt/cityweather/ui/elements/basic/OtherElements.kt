package ua.sviatkuzbyt.cityweather.ui.elements.basic

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.ui.theme.spaceLarge
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium
import ua.sviatkuzbyt.cityweather.ui.theme.spaceSmall

@Composable
fun SpaceMedium(){
    Spacer(Modifier.height(spaceMedium))
}

@Composable
fun SpaceSmall(){
    Spacer(Modifier.height(spaceSmall))
}

val containerShape = RoundedCornerShape(spaceLarge)