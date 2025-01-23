package ua.sviatkuzbyt.cityweather.data.api.forecast

import androidx.annotation.DrawableRes
import ua.sviatkuzbyt.cityweather.data.api.current.Weather
import ua.sviatkuzbyt.cityweather.data.api.current.Wind

data class ForecastData(
    val time: String,
    val temp: String,
    val windSpeed: String? = null,
    @DrawableRes val weatherIcon: Int,
    val contentDescription: String
)

data class ForecastResponse(
    val list: List<ForecastResponseItem>
)

data class ForecastResponseItem(
    val dt: Long,
    val main: Main,
    val wind: Wind,
    val weather: List<Weather>
)

data class Main(
    val temp: Float
)