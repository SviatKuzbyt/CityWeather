package ua.sviatkuzbyt.cityweather.data.structures.weather.forecast

data class ForecastData(
    val time: String,
    val temp: String,
    val windSpeed: String? = null,
    val weatherIcon: String,
)

enum class ForecastType { TODAY, FIVE_DAYS }