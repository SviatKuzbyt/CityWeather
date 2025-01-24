package ua.sviatkuzbyt.cityweather.data.api.forecast.fivedays

import ua.sviatkuzbyt.cityweather.data.api.current.Weather

data class ToFormatFiveDayForecastData(
    val time: String,
    val temp: Int,
    val weatherIcon: String,
    val contentDescription: String
)

data class ForecastFiveDayDataResponse(
    val list: List<ForecastFiveDaysResponseItem>
)

data class ForecastFiveDaysResponseItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>
)

data class Main(
    val temp: Float
)