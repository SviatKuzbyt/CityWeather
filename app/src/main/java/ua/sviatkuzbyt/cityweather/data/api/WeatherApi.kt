package ua.sviatkuzbyt.cityweather.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ua.sviatkuzbyt.cityweather.data.structures.weather.cities.WeatherResponse

interface WeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String = "standard",
        @Query("appid") apiKey: String = API_KEY
    ): WeatherResponse
}