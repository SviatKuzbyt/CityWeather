package ua.sviatkuzbyt.cityweather.data.api.current

data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val rain: Rain?
)

data class Weather(
    val icon: String,
    val description: String
)

data class Main(
    val temp: Float,
    val feels_like: Float,
    val pressure: Int,
    val humidity: Int
)

data class Wind(
    val speed: Float
)

data class Rain(
    val `1h`: Float
)