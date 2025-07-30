package ua.sviatkuzbyt.cityweather.data.structures.weather.forecast

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ForecastData(
    val time: String,
    val temp: String,
    val windSpeed: String? = null,
    @DrawableRes val weatherIcon: Int,
    @StringRes val weatherDescription: Int
)

data class Main(val temp: Float)