package ua.sviatkuzbyt.cityweather.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ua.sviatkuzbyt.cityweather.data.structures.weather.cities.WeatherResponse
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastFiveDaysResponse
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastTodayResponse

interface WeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String = "standard",
        @Query("appid") apiKey: String = API_KEY
    ): WeatherResponse

    @GET("forecast")
    suspend fun getForecastToday(
        @Query("q") city: String,
        @Query("units") units: String = "standard",
        @Query("cnt") count: Int = 8,
        @Query("appid") apiKey: String = API_KEY
    ): ForecastTodayResponse

    @GET("forecast")
    suspend fun getForecastFiveDays(
        @Query("q") city: String,
        @Query("units") units: String = "standard",
        @Query("appid") apiKey: String = API_KEY
    ): ForecastFiveDaysResponse
}