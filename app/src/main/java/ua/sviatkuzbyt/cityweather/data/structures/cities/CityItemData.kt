package ua.sviatkuzbyt.cityweather.data.structures.cities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable

@Stable
data class CityItemData(
    val cityId: Long,
    val name: String,
    val temperature: String,
    val windSpeed: String,
    @DrawableRes val image: Int,
    val weatherDescription: String,
    val background: CityBackground,
    val humidity: Int,
    val pressure: Int,
    val feelsLike: String,
    val rain: Int
)

data class CityItemDetailData(
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
    val textContent: String
)