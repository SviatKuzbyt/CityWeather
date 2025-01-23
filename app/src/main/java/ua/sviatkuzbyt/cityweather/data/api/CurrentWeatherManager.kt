package ua.sviatkuzbyt.cityweather.data.api
import com.google.gson.Gson
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.database.CityEntity
import ua.sviatkuzbyt.cityweather.data.structures.CityBackground
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import ua.sviatkuzbyt.cityweather.data.structures.WeatherItemAppearance
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
        return weatherAppearance[code]
            ?: WeatherItemAppearance(R.drawable.unknown, CityBackground.BLue)
    }

    private val weatherAppearance = mapOf(
        "01d" to WeatherItemAppearance(R.drawable.weather_01d, CityBackground.BLue),
        "01n" to WeatherItemAppearance(R.drawable.weather_01n, CityBackground.BlueDark),
        "02d" to WeatherItemAppearance(R.drawable.weather_02d, CityBackground.BLue),
        "02n" to WeatherItemAppearance(R.drawable.weather_02n, CityBackground.BlueDark),
        "03d" to WeatherItemAppearance(R.drawable.weather_03, CityBackground.White),
        "03n" to WeatherItemAppearance(R.drawable.weather_03, CityBackground.WhiteDark),
        "04d" to WeatherItemAppearance(R.drawable.weather_04, CityBackground.Gray),
        "04n" to WeatherItemAppearance(R.drawable.weather_04, CityBackground.GrayDark),
        "09d" to WeatherItemAppearance(R.drawable.weather_09, CityBackground.Gray),
        "09n" to WeatherItemAppearance(R.drawable.weather_09, CityBackground.GrayDark),
        "10d" to WeatherItemAppearance(R.drawable.weather_10d, CityBackground.White),
        "10n" to WeatherItemAppearance(R.drawable.weather_10n, CityBackground.WhiteDark),
        "11d" to WeatherItemAppearance(R.drawable.weather_11, CityBackground.Gray),
        "11n" to WeatherItemAppearance(R.drawable.weather_11, CityBackground.GrayDark),
        "13d" to WeatherItemAppearance(R.drawable.weather_13, CityBackground.White),
        "13n" to WeatherItemAppearance(R.drawable.weather_13, CityBackground.WhiteDark),
        "50d" to WeatherItemAppearance(R.drawable.weather_50, CityBackground.White),
        "50n" to WeatherItemAppearance(R.drawable.weather_50, CityBackground.WhiteDark)
    )
}