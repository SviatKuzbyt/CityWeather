package ua.sviatkuzbyt.cityweather.data.structures

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable

enum class CityBackground{
    BLue, BlueDark, White, WhiteDark, Gray, GrayDark
}

@Stable
data class CityData(
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