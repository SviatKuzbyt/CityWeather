package ua.sviatkuzbyt.cityweather.data.structures.forecast

import ua.sviatkuzbyt.cityweather.data.structures.currentweather.Weather

data class ForecastFiveDaysResponse(
    val list: List<ForecastFiveDaysItem>
)

data class ForecastFiveDaysItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>
)

data class NoFormatFiveDaysData(
    val time: String,
    val temp: Int,
    val weatherIcon: String,
    val contentDescription: String
)