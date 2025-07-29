package ua.sviatkuzbyt.cityweather.data.structures.weather.forecast

import androidx.annotation.DrawableRes

data class ForecastData(
    val time: String,
    val temp: String,
    val windSpeed: String? = null,
    @DrawableRes val weatherIcon: Int,
    val contentDescription: String
)

data class Main(
    val temp: Float
)