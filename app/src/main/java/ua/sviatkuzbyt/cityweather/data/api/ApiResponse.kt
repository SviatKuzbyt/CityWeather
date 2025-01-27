package ua.sviatkuzbyt.cityweather.data.api

import java.net.URL

fun getApiResponse(city: String, service: String, params: String = ""): String{
    println("SKLT https://api.openweathermap.org/data/2.5/$service?q=$city&appid=$API_KEY$params")
    return URL(
        "https://api.openweathermap.org/data/2.5/$service?q=$city&appid=$API_KEY$params"
    ).readText()
}