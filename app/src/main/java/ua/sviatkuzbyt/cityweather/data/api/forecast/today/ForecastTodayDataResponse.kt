package ua.sviatkuzbyt.cityweather.data.api.forecast.today

import ua.sviatkuzbyt.cityweather.data.api.current.Weather
import ua.sviatkuzbyt.cityweather.data.api.current.Wind

data class ForecastTodayDataResponse(
    val list: List<ForecastTodayResponseItem>
)

data class ForecastTodayResponseItem(
    val dt: Long,
    val main: Main,
    val wind: Wind,
    val weather: List<Weather>
)

data class Main(
    val temp: Float
)