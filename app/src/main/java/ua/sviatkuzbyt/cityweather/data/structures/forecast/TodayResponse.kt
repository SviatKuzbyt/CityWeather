package ua.sviatkuzbyt.cityweather.data.structures.forecast

import ua.sviatkuzbyt.cityweather.data.structures.weather.Weather
import ua.sviatkuzbyt.cityweather.data.structures.weather.Wind

data class ForecastTodayDataResponse(
    val list: List<ForecastTodayResponseItem>
)

data class ForecastTodayResponseItem(
    val dt: Long,
    val main: Main,
    val wind: Wind,
    val weather: List<Weather>
)