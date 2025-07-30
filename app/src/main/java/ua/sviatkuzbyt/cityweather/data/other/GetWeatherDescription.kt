package ua.sviatkuzbyt.cityweather.data.other

import ua.sviatkuzbyt.cityweather.R

fun getWeatherDescription(icon: String) = when (icon) {
    "01d", "01n" -> R.string.weather_clear
    "02d", "02n" -> R.string.weather_bit_cloudy
    "03d", "03n", "04d", "04n" -> R.string.weather_cloudy
    "09d", "09n" -> R.string.weather_rain
    "10d", "10n" -> R.string.weather_light_rain
    "11d", "11n" -> R.string.weather_thunderstorm
    "13d", "13n" -> R.string.weather_snow
    "50d", "50n" -> R.string.weather_foggy
    else -> R.string.weather_unknown
}