package ua.sviatkuzbyt.cityweather.data.api

import com.google.gson.Gson
import ua.sviatkuzbyt.cityweather.data.structures.weather.WeatherItemAppearance.Companion.getWeatherItemAppearance
import ua.sviatkuzbyt.cityweather.data.database.CityEntity
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemData
import ua.sviatkuzbyt.cityweather.data.structures.weather.UnitsData
import ua.sviatkuzbyt.cityweather.data.structures.weather.WeatherResponse

class CurrentWeatherManager{
    private val gson = Gson()

    fun loadWeatherForCity(cityEntity: CityEntity, units: UnitsData): CityItemData {
        val apiResponse = getApiResponse(cityEntity.name, "weather", "&units=${units.unitsApi}")
        return convertToCityItem(apiResponse, cityEntity, units.temp, units.wind)
    }

    private fun convertToCityItem(apiResponse: String, cityEntity: CityEntity, temp: Char, wind: String): CityItemData {
        val gsonResponse = gson.fromJson(apiResponse, WeatherResponse::class.java)
        val appearance = getWeatherItemAppearance(gsonResponse.weather[0].icon)

        return CityItemData(
            cityId = cityEntity.id,
            name = cityEntity.name,
            temperature = "${gsonResponse.main.temp.toInt()}$temp",
            windSpeed = "${gsonResponse.wind.speed} $wind",
            image = appearance.icon,
            weatherDescription = gsonResponse.weather[0].description,
            background = appearance.background,
            humidity = gsonResponse.main.humidity,
            pressure = gsonResponse.main.pressure,
            feelsLike = "${gsonResponse.main.feels_like.toInt()}$temp",
            rain = gsonResponse.rain?.`1h`?.toInt() ?: 0
        )
    }
}