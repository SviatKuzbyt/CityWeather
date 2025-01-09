package ua.sviatkuzbyt.cityweather.data.structures

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import ua.sviatkuzbyt.cityweather.ui.theme.BlackDark
import ua.sviatkuzbyt.cityweather.ui.theme.BlackDarkTransparent
import ua.sviatkuzbyt.cityweather.ui.theme.BlackDarkTransparentLow
import ua.sviatkuzbyt.cityweather.ui.theme.WhiteTransparent
import ua.sviatkuzbyt.cityweather.ui.theme.WhiteTransparentLow

enum class CityBackground{
    BLue, BlueDark, White, WhiteDark, Gray, GrayDark
}

@Stable
data class CityItemData(
    val cityId: Int,
    val name: String,
    val temperature: String,
    val windSpeed: String,
    @DrawableRes val image: Int,
    @StringRes val weatherDescription: Int,
    val background: CityBackground,
    val humidity: Int,
    val pressure: String,
    val feelsLike: String,
    val rain: Int
)

data class CityItemDetailData(
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
    val textContent: String
)

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