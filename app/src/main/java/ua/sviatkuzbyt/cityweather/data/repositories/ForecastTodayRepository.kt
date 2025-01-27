package ua.sviatkuzbyt.cityweather.data.repositories

import ua.sviatkuzbyt.cityweather.data.structures.weather.WeatherItemAppearance.Companion.getWeatherItemAppearance
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.data.api.ForecastManager
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastTodayDataResponse
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastTodayResponseItem
import ua.sviatkuzbyt.cityweather.data.api.getApiResponse

class ForecastTodayRepository(city: String): ForecastManager(city) {
    override fun loadForecast(): List<ForecastData> {
        val apiResponse = getApiResponse(city, "forecast", "&cnt=8")
        val forecastTodayResponse = gson.fromJson(apiResponse, ForecastTodayDataResponse::class.java)
        return formatData(forecastTodayResponse.list)
    }

    private fun formatData(data: List<ForecastTodayResponseItem>): List<ForecastData>{
        return data.map {
            ForecastData(
                time = convertTime(it.dt, "HH:mm"),
                temp = "${it.main.temp.toInt()}℃",
                windSpeed = "${it.wind.speed.toInt()} m/s",
                weatherIcon = getWeatherItemAppearance(it.weather[0].icon).icon,
                contentDescription = it.weather[0].description
            )
        }
    }
}