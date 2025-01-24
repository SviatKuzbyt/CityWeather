package ua.sviatkuzbyt.cityweather.data.api

import com.google.gson.Gson
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastData
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

abstract class ForecastManager(protected val city: String) {
    protected val gson = Gson()

    abstract fun loadForecast(): List<ForecastData>

    protected fun convertTime(time: Long, pattern: String): String{
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return Instant.ofEpochSecond(time)
            .atZone(ZoneId.systemDefault())
            .format(formatter)
    }
}