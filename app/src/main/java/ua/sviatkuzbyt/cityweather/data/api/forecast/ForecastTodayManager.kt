package ua.sviatkuzbyt.cityweather.data.api.forecast

import com.google.gson.Gson
import ua.sviatkuzbyt.cityweather.data.api.WeatherItemAppearance.Companion.getWeatherItemAppearance
import ua.sviatkuzbyt.cityweather.data.api.getApiResponse
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ForecastTodayManager {
    fun loadForecast(city: String): List<ForecastData>{
        val apiResponse = getApiResponse(city, "forecast", "&cnt=8")
        val gson = Gson()
        val convertedForecastResponse = gson.fromJson(apiResponse, ForecastResponse::class.java)

        return convertedForecastResponse.list.map {
            ForecastData(
                time = convertLongToTimeString(it.dt),
                temp = "${it.main.temp.toInt()}℃",
                windSpeed = "${it.wind.speed.toInt()} m/s",
                weatherIcon = getWeatherItemAppearance(it.weather[0].icon).icon,
                contentDescription = it.weather[0].description
            )
        }
    }

    private fun convertLongToTimeString(timestamp: Long): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return Instant.ofEpochSecond(timestamp)
            .atZone(ZoneId.systemDefault())
            .format(formatter)
    }
}