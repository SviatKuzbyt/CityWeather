package ua.sviatkuzbyt.cityweather.data.repositories

import com.google.gson.Gson
import ua.sviatkuzbyt.cityweather.data.api.getApiResponse
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastFiveDaysItem
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastFiveDaysResponse
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastTodayDataResponse
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastTodayResponseItem
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.NoFormatFiveDaysData
import ua.sviatkuzbyt.cityweather.data.structures.weather.UnitsData
import ua.sviatkuzbyt.cityweather.data.structures.weather.WeatherItemAppearance.Companion.getWeatherItemAppearance
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ForecastRepository(
    private val settingsRepository: SettingsRepository,
    private val gson: Gson
) {
    suspend fun loadForecastToday(city: String): List<ForecastData> {
        val units = UnitsData(settingsRepository.getSettings(SettingsId.Units))
        val apiResponse = getApiResponse(city, "forecast", "&cnt=8&units=${units.unitsApi}")
        val forecastTodayResponse = gson.fromJson(apiResponse, ForecastTodayDataResponse::class.java)
        return formatDataToday(forecastTodayResponse.list, units.temp, units.wind)
    }

    suspend fun loadForecastFiveDays(city: String): List<ForecastData> {
        val units = UnitsData(settingsRepository.getSettings(SettingsId.Units))
        val apiResponse = getApiResponse(city, "forecast", "&units=${units.unitsApi}")
        val forecastFiveDayResponse = gson.fromJson(apiResponse, ForecastFiveDaysResponse::class.java)
        return formatDataFiveDays(forecastFiveDayResponse.list, units.temp)
    }

    private fun formatDataToday(data: List<ForecastTodayResponseItem>, temp: Char, wind: String): List<ForecastData>{
        return data.map {
            ForecastData(
                time = convertTime(it.dt, "HH:mm"),
                temp = "${it.main.temp.toInt()}$temp",
                windSpeed = "${it.wind.speed.toInt()} $wind",
                weatherIcon = getWeatherItemAppearance(it.weather[0].icon).icon,
                contentDescription = "Temp description"
            )
        }
    }

    private fun convertTime(time: Long, pattern: String): String{
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return Instant.ofEpochSecond(time)
            .atZone(ZoneId.systemDefault())
            .format(formatter)
    }

    private fun formatDataFiveDays(data: List<ForecastFiveDaysItem>, temp: Char): List<ForecastData>{
        val noFormatDataList = data.map {
            NoFormatFiveDaysData(
                time = convertTime(it.dt, "E, dd.MM"),
                temp =it.main.temp.toInt(),
                weatherIcon = it.weather[0].icon,
                contentDescription = "Temp description"
            )
        }

        val groupByDays = noFormatDataList
            .groupBy { it.time }
            .filter { it.value.size > 4}

        return groupByDays.map { dayData ->
            ForecastData(
                time = dayData.key,
                temp = getMinMaxTemp(dayData.value, temp),
                weatherIcon = getWeatherItemAppearance(dayData.value[3].weatherIcon).icon,
                contentDescription = dayData.value[3].contentDescription
            )
        }
    }

    private fun getMinMaxTemp(data: List<NoFormatFiveDaysData>, temp: Char): String{
        val max = data.maxBy { it.temp }.temp
        val min = data.minBy { it.temp }.temp
        return "$max$temp / $min$temp"
    }
}