package ua.sviatkuzbyt.cityweather.data.structures.weather.forecast

import ua.sviatkuzbyt.cityweather.data.structures.weather.cities.Weather
import ua.sviatkuzbyt.cityweather.data.structures.weather.cities.Wind

data class ForecastTodayResponse(
    val list: List<ForecastTodayItem>
)

data class ForecastTodayItem(
    val dt: Long,
    val main: Main,
    val wind: Wind,
    val weather: List<Weather>
)