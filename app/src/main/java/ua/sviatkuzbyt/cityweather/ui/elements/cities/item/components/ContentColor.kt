package ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import ua.sviatkuzbyt.cityweather.data.structures.CityItemColors

fun getCityItemColors(backgroundColor: Color): CityItemColors {
    return if (backgroundColor.luminance() > 0.5f){
        CityItemColors.darkColors
    } else{
        CityItemColors.lightColors
    }
}