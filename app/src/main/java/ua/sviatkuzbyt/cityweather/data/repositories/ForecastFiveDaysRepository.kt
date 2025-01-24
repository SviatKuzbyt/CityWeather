package ua.sviatkuzbyt.cityweather.data.repositories

import ua.sviatkuzbyt.cityweather.data.structures.WeatherItemAppearance.Companion.getWeatherItemAppearance
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.data.api.ForecastManager
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastFiveDaysResponse
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastFiveDaysItem
import ua.sviatkuzbyt.cityweather.data.structures.forecast.NoFormatFiveDaysData
import ua.sviatkuzbyt.cityweather.data.api.getApiResponse

class ForecastFiveDaysRepository(city: String): ForecastManager(city){

    override fun loadForecast(): List<ForecastData> {
        val apiResponse = getApiResponse(city, "forecast")
        val forecastFiveDayResponse = gson.fromJson(apiResponse, ForecastFiveDaysResponse::class.java)
        return formatData(forecastFiveDayResponse.list)
    }

    private fun formatData(data: List<ForecastFiveDaysItem>): List<ForecastData>{
        val toFormatList = data.map {
            NoFormatFiveDaysData(
                time = convertTime(it.dt, "E, dd.MM"),
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

    private fun getMinMaxTemp(data: List<NoFormatFiveDaysData>): String{
        val max = data.maxBy { it.temp }.temp
        val min = data.minBy { it.temp }.temp
        return "$max℃ / $min℃"
    }
}