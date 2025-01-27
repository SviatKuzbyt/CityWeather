package ua.sviatkuzbyt.cityweather.data.repositories

import android.content.Context
import ua.sviatkuzbyt.cityweather.data.structures.weather.WeatherItemAppearance.Companion.getWeatherItemAppearance
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.data.api.ForecastManager
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastTodayDataResponse
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastTodayResponseItem
import ua.sviatkuzbyt.cityweather.data.api.getApiResponse
import ua.sviatkuzbyt.cityweather.data.settingsstore.SettingsStoreManager
import ua.sviatkuzbyt.cityweather.data.structures.weather.UnitsData

class ForecastTodayRepository(city: String, private val context: Context): ForecastManager(city) {
    override suspend fun loadForecast(): List<ForecastData> {
        val units = UnitsData(SettingsStoreManager.getUnits(context))
        val apiResponse = getApiResponse(city, "forecast", "&cnt=8&units=${units.unitsApi}")
        val forecastTodayResponse = gson.fromJson(apiResponse, ForecastTodayDataResponse::class.java)
        return formatData(forecastTodayResponse.list, units.temp, units.wind)
    }

    private fun formatData(data: List<ForecastTodayResponseItem>, temp: Char, wind: String): List<ForecastData>{
        return data.map {
            ForecastData(
                time = convertTime(it.dt, "HH:mm"),
                temp = "${it.main.temp.toInt()}$temp",
                windSpeed = "${it.wind.speed.toInt()} $wind",
                weatherIcon = getWeatherItemAppearance(it.weather[0].icon).icon,
                contentDescription = it.weather[0].description
            )
        }
    }
}