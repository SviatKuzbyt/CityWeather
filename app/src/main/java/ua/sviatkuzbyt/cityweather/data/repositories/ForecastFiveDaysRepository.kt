package ua.sviatkuzbyt.cityweather.data.repositories

import android.content.Context
import ua.sviatkuzbyt.cityweather.data.structures.weather.WeatherItemAppearance.Companion.getWeatherItemAppearance
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.data.api.ForecastManager
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastFiveDaysResponse
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastFiveDaysItem
import ua.sviatkuzbyt.cityweather.data.structures.forecast.NoFormatFiveDaysData
import ua.sviatkuzbyt.cityweather.data.api.getApiResponse
import ua.sviatkuzbyt.cityweather.data.settingsstore.SettingsStoreManager
import ua.sviatkuzbyt.cityweather.data.structures.weather.UnitsData

class ForecastFiveDaysRepository(city: String, private val context: Context): ForecastManager(city){

    override suspend fun loadForecast(): List<ForecastData> {
        val units = UnitsData(SettingsStoreManager.getUnits(context))
        val apiResponse = getApiResponse(city, "forecast", "&units=${units.unitsApi}")
        val forecastFiveDayResponse = gson.fromJson(apiResponse, ForecastFiveDaysResponse::class.java)
        return formatData(forecastFiveDayResponse.list, units.temp)
    }

    private fun formatData(data: List<ForecastFiveDaysItem>, temp: Char): List<ForecastData>{
        val noFormatDataList = data.map {
            NoFormatFiveDaysData(
                time = convertTime(it.dt, "E, dd.MM"),
                temp =it.main.temp.toInt(),
                weatherIcon = it.weather[0].icon,
                contentDescription = it.weather[0].description
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