package ua.sviatkuzbyt.cityweather.data.api
import com.google.gson.Gson
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.database.CityEntity
import ua.sviatkuzbyt.cityweather.data.structures.CityBackground
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import ua.sviatkuzbyt.cityweather.data.structures.WeatherResponse
import java.net.URL

class CurrentWeatherManager{
    private val gson = Gson()

    fun loadWeatherForCity(cityEntity: CityEntity): CityItemData{
        val apiResponse = getDataFromApi(cityEntity.name)
        return convertToCityItem(apiResponse, cityEntity)
    }

    private fun getDataFromApi(city: String): String{
        return URL(
            "https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$API_KEY"
        ).readText()
    }

    fun convertToCityItem(apiResponse: String, cityEntity: CityEntity): CityItemData{
        val gsonResponse = gson.fromJson(apiResponse, WeatherResponse::class.java)
        val appearance = getAppearance(gsonResponse.weather[0].icon)

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

    private fun getAppearance(code: String): WeatherItemAppearance {
        return WeatherItemAppearance.items[code]
            ?: WeatherItemAppearance(R.drawable.unknown, CityBackground.BLue)
    }
}