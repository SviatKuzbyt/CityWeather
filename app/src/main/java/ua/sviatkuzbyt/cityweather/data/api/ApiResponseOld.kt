package ua.sviatkuzbyt.cityweather.data.api

import java.net.URL

//TODO: remove this file when the app is updated to use the new API
fun getApiResponse(city: String, service: String, params: String = ""): String{
    return URL(
        "https://api.openweathermap.org/data/2.5/$service?q=$city&appid=$API_KEY$params"
    ).readText()
}