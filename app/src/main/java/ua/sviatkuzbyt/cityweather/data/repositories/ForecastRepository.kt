package ua.sviatkuzbyt.cityweather.data.repositories

import ua.sviatkuzbyt.cityweather.data.api.WeatherApi
import ua.sviatkuzbyt.cityweather.data.other.getWeatherDescription
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastFiveDaysItem
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastTodayItem
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.NoFormatFiveDaysData
import ua.sviatkuzbyt.cityweather.data.structures.weather.UnitsData
import ua.sviatkuzbyt.cityweather.data.structures.weather.WeatherItemAppearance.Companion.getWeatherItemAppearance
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ForecastRepository(
    private val settingsRepository: SettingsRepository,
    private val weatherApi: WeatherApi
) {
    suspend fun loadForecastToday(city: String): List<ForecastData> {
        val units = UnitsData(settingsRepository.getSettings(SettingsId.Units))
        val apiResponse = weatherApi.getForecastToday(city, units.unitsApi)
        return formatDataToday(apiResponse.list, units.temp, units.wind)
    }

    suspend fun loadForecastFiveDays(city: String): List<ForecastData> {
        val units = UnitsData(settingsRepository.getSettings(SettingsId.Units))
        val apiResponse = weatherApi.getForecastFiveDays(city, units.unitsApi)
        return formatDataFiveDays(apiResponse.list, units.temp)
    }

    private fun formatDataToday(data: List<ForecastTodayItem>, temp: Char, wind: String): List<ForecastData>{
        return data.map {
            ForecastData(
                time = convertTime(it.dt, "HH:mm"),
                temp = "${it.main.temp.toInt()}$temp",
                windSpeed = "${it.wind.speed.toInt()} $wind",
                weatherIcon = getWeatherItemAppearance(it.weather[0].icon).icon,
                weatherDescription = getWeatherDescription(it.weather[0].icon)
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
                weatherIcon = it.weather[0].icon
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
                weatherDescription = getWeatherDescription(dayData.value[3].weatherIcon)
            )
        }
    }

    private fun getMinMaxTemp(data: List<NoFormatFiveDaysData>, temp: Char): String{
        val max = data.maxBy { it.temp }.temp
        val min = data.minBy { it.temp }.temp
        return "$max$temp / $min$temp"
    }
}