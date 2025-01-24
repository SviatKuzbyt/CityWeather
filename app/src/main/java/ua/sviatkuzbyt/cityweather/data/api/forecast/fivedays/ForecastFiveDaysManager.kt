package ua.sviatkuzbyt.cityweather.data.api.forecast.fivedays

import com.google.gson.Gson
import ua.sviatkuzbyt.cityweather.data.api.WeatherItemAppearance.Companion.getWeatherItemAppearance
import ua.sviatkuzbyt.cityweather.data.api.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.data.api.getApiResponse
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ForecastFiveDaysManager {
    private val gson = Gson()

    fun loadForecast(city: String): List<ForecastData>{
        val apiResponse = getApiResponse(city, "forecast")
        val forecastFiveDayResponse = gson.fromJson(apiResponse, ForecastFiveDayDataResponse::class.java)
        return formatData(forecastFiveDayResponse.list)
    }

    private fun formatData(data: List<ForecastFiveDaysResponseItem>): List<ForecastData>{
        val toFormatList = data.map {
            ToFormatFiveDayForecastData(
                time = convertLongToTimeString(it.dt),
                temp =it.main.temp.toInt(),
                weatherIcon = it.weather[0].icon,
                contentDescription = it.weather[0].description
            )
        }

        val groupByDays = toFormatList
            .groupBy { it.time }
            .filter { it.value.size > 4}

        return groupByDays.map { dayData ->
            ForecastData(
                time = dayData.key,
                temp = getMinMaxTemp(dayData.value),
                weatherIcon = getWeatherItemAppearance(dayData.value[3].weatherIcon).icon,
                contentDescription = dayData.value[3].contentDescription
            )
        }
    }

    private fun convertLongToTimeString(timestamp: Long): String {
        val formatter = DateTimeFormatter.ofPattern("E, dd.MM")
        return Instant.ofEpochSecond(timestamp)
            .atZone(ZoneId.systemDefault())
            .format(formatter)
    }

    private fun getMinMaxTemp(data: List<ToFormatFiveDayForecastData>): String{
        val max = data.maxBy { it.temp }.temp
        val min = data.minBy { it.temp }.temp
        return "$max℃ / $min℃"
    }
}