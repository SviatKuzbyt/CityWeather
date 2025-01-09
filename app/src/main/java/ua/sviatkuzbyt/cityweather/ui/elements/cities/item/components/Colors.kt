package ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import ua.sviatkuzbyt.cityweather.data.structures.CityBackground
import ua.sviatkuzbyt.cityweather.data.structures.CityItemColors

fun getLinearBackground(backgrounds: List<Color>) =
    Brush.linearGradient(
        colors = backgrounds,
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, 0f)
    )

fun getCityBackground(background: CityBackground) =
    when(background){
        CityBackground.BLue -> {
            listOf(Color(0xFF37AFE1),  Color(0xFF008DDA))
        }
        CityBackground.BlueDark -> {
            listOf(Color(0xFF003285),  Color(0xFF071952))
        }
        CityBackground.White -> {
            listOf(Color(0xFFD2E4F1),  Color(0xFF8FACC0))
        }
        CityBackground.WhiteDark -> {
            listOf(Color(0xFF6C7EA1),  Color(0xFF4B6389))
        }
        CityBackground.Gray -> {
            listOf(Color(0xFF6C7782),  Color(0xFF8C98A5))
        }
        CityBackground.GrayDark -> {
            listOf(Color(0xFF263045),  Color(0xFF465169))
        }
    }

fun getCityItemColors(backgroundColor: Color): CityItemColors {
    return if (backgroundColor.luminance() > 0.5f){
        CityItemColors.darkColors
    } else{
        CityItemColors.lightColors
    }
}