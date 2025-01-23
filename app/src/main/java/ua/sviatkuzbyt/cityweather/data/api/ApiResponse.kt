package ua.sviatkuzbyt.cityweather.data.api

import java.net.URL

fun getApiResponse(city: String, service: String, params: String = ""): String{
    return URL(
        "https://api.openweathermap.org/data/2.5/$service?q=$city&units=metric&appid=$API_KEY$params"
    ).readText()
}