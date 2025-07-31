package ua.sviatkuzbyt.cityweather.data.structures.weather.cities

import androidx.compose.ui.graphics.Color
import ua.sviatkuzbyt.cityweather.ui.theme.BlackDark
import ua.sviatkuzbyt.cityweather.ui.theme.BlackDarkTransparent
import ua.sviatkuzbyt.cityweather.ui.theme.BlackDarkTransparentLow
import ua.sviatkuzbyt.cityweather.ui.theme.WhiteTransparent
import ua.sviatkuzbyt.cityweather.ui.theme.WhiteTransparentLow

data class CityItemColors(
    val textColor: Color,
    val iconColor: Color,
    val backColor: Color
){
    companion object {
        val lightColors = CityItemColors(
            Color.White, WhiteTransparent, WhiteTransparentLow
        )

        val darkColors = CityItemColors(
            BlackDark, BlackDarkTransparent, BlackDarkTransparentLow
        )
    }
}

enum class CityBackground {
    BLue, BlueDark, White, WhiteDark, Gray, GrayDark
}