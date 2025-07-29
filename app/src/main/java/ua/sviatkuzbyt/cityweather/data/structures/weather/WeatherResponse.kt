package ua.sviatkuzbyt.cityweather.data.structures.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val weather: List<Weather> = listOf(),
    val main: Main = Main(),
    val wind: Wind = Wind(),
    val rain: Rain? = null
)

data class Weather(val icon: String = "")

data class Main(
    val temp: Float = 0f,
    @SerializedName("feels_like") val feelsLike: Float = 0f,
    val pressure: Int = 0,
    val humidity: Int = 0
)

data class Wind(
    val speed: Float = 0f
)

data class Rain(
    @SerializedName("1h") val percent: Float = 0f
)