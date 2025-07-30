package ua.sviatkuzbyt.cityweather.data.repositories

import android.content.Context
import ua.sviatkuzbyt.cityweather.data.api.WeatherApi
import ua.sviatkuzbyt.cityweather.data.database.DataBaseDao
import ua.sviatkuzbyt.cityweather.data.database.entities.ForecastEntity
import ua.sviatkuzbyt.cityweather.data.other.NoConnectException
import ua.sviatkuzbyt.cityweather.data.other.canLoad
import ua.sviatkuzbyt.cityweather.data.other.getWeatherDescription
import ua.sviatkuzbyt.cityweather.data.other.isConnected
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastFiveDaysItem
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastTodayItem
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.NoFormatFiveDaysData
import ua.sviatkuzbyt.cityweather.data.structures.weather.UnitsData
import ua.sviatkuzbyt.cityweather.data.structures.weather.WeatherItemAppearance.Companion.getWeatherItemAppearance
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastDataUI
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastType
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ForecastRepository(
    private val settingsRepository: SettingsRepository,
    private val weatherApi: WeatherApi,
    private val dao: DataBaseDao,
    private val context: Context
) {

    suspend fun loadForecast(cityId: Long, type: ForecastType): List<ForecastDataUI> {
        val units = UnitsData(settingsRepository.getSettings(SettingsId.Units))
        val entity = dao.getForecast(cityId, type)

        val isOnline = isConnected(context)

        return when {
            entity != null -> {
                if (isOnline && canLoad(entity.time, units.unitsApi, entity.units)) {
                    loadFromApiAndSave(cityId, type, units, entity)
                } else {
                    formatForecastDataUI(entity.data)
                }
            }

            isOnline -> {
                loadFromApiAndSave(cityId, type, units)
            }

            else -> throw NoConnectException()
        }
    }

    private suspend fun loadFromApiAndSave(
        cityId: Long,
        type: ForecastType,
        units: UnitsData,
        oldEntity: ForecastEntity? = null
    ): List<ForecastDataUI> {
        val city = dao.getCityName(cityId)
        val data = getForecastFromAPI(type, city, units)

        val entity = ForecastEntity(
            cityId = cityId,
            type = type,
            units = units.unitsApi,
            time = System.currentTimeMillis(),
            data = data
        )

        if (oldEntity == null) {
            dao.addForecast(entity)
        } else {
            dao.updateForecast(entity.copy(forecastId = oldEntity.forecastId))
        }

        return formatForecastDataUI(data)
    }

    private suspend fun getForecastFromAPI(
        type: ForecastType,
        city: String,
        units: UnitsData
    ): List<ForecastData> = when (type) {
        ForecastType.TODAY -> {
            val apiResponse = weatherApi.getForecastToday(city, units.unitsApi)
            formatDataToday(apiResponse.list, units)
        }

        ForecastType.FIVE_DAYS -> {
            val apiResponse = weatherApi.getForecastFiveDays(city, units.unitsApi)
            formatDataFiveDays(apiResponse.list, units)
        }
    }

    private fun formatForecastDataUI(data: List<ForecastData>) = data.map {
        ForecastDataUI(
            time = it.time,
            temp = it.temp,
            windSpeed = it.windSpeed,
            weatherIcon = getWeatherItemAppearance(it.weatherIcon).icon,
            weatherDescription = getWeatherDescription(it.weatherIcon)
        )
    }

    private fun formatDataToday(
        data: List<ForecastTodayItem>,
        units: UnitsData
    ) = data.map {
        ForecastData(
            time = convertTime(it.dt, "HH:mm"),
            temp = "${it.main.temp.toInt()}${units.temp}",
            windSpeed = "${it.wind.speed.toInt()} ${units.wind}",
            weatherIcon = it.weather[0].icon
        )
    }

    private fun formatDataFiveDays(
        data: List<ForecastFiveDaysItem>,
        units: UnitsData
    ): List<ForecastData> {
        val grouped = data.map {
            NoFormatFiveDaysData(
                time = convertTime(it.dt, "E, dd.MM"),
                temp = it.main.temp.toInt(),
                weatherIcon = it.weather[0].icon
            )
        }.groupBy { it.time }
            .filter { it.value.size > 4 }

        return grouped.map { (day, values) ->
            ForecastData(
                time = day,
                temp = getMinMaxTemp(values, units.temp),
                weatherIcon = values[3].weatherIcon
            )
        }
    }

    private fun getMinMaxTemp(data: List<NoFormatFiveDaysData>, temp: Char): String {
        val max = data.maxBy { it.temp }.temp
        val min = data.minBy { it.temp }.temp
        return "$max$temp / $min$temp"
    }

    private fun convertTime(time: Long, pattern: String): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return Instant.ofEpochSecond(time)
            .atZone(ZoneId.systemDefault())
            .format(formatter)
    }
}