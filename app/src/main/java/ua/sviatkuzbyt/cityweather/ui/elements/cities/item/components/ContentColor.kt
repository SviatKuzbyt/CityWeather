package ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import ua.sviatkuzbyt.cityweather.ui.theme.Black
import ua.sviatkuzbyt.cityweather.ui.theme.BlackTransparent
import ua.sviatkuzbyt.cityweather.ui.theme.WhiteTransparent

fun getContrastingColor(backgroundColor: Color): Color {
    return if (backgroundColor.luminance() > 0.5f) {
        Black
    } else {
        Color.White
    }
}

fun getContrastingTransparentColor(backgroundColor: Color): Color {
    return if (backgroundColor.luminance() > 0.5f) {
        BlackTransparent
    } else {
        WhiteTransparent
    }
}