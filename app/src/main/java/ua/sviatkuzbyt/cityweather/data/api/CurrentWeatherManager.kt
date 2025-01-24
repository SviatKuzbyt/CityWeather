package ua.sviatkuzbyt.cityweather.data.api
import com.google.gson.Gson
import ua.sviatkuzbyt.cityweather.data.structures.WeatherItemAppearance.Companion.getWeatherItemAppearance
import ua.sviatkuzbyt.cityweather.data.database.CityEntity
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemData
import ua.sviatkuzbyt.cityweather.data.structures.currentweather.WeatherResponse

class CurrentWeatherManager{
    private val gson = Gson()

    fun loadWeatherForCity(cityEntity: CityEntity): CityItemData {
        val apiResponse = getApiResponse(cityEntity.name, "weather")
        return convertToCityItem(apiResponse, cityEntity)
    }

    private fun convertToCityItem(apiResponse: String, cityEntity: CityEntity): CityItemData {
        val gsonResponse = gson.fromJson(apiResponse, WeatherResponse::class.java)
        val appearance = getWeatherItemAppearance(gsonResponse.weather[0].icon)

        return CityItemData(
            cityId = cityEntity.id,
            name = cityEntity.name,
            temperature = "${gsonResponse.main.temp.toInt()}℃",
            windSpeed = "${gsonResponse.wind.speed} m/s",
            image = appearance.icon,
            weatherDescription = gsonResponse.weather[0].description,
            background = appearance.background,
            humidity = gsonResponse.main.humidity,
            pressure = gsonResponse.main.pressure,
            feelsLike = "${gsonResponse.main.feels_like.toInt()}℃",
            rain = gsonResponse.rain?.`1h`?.toInt() ?: 0
        )
    }
}