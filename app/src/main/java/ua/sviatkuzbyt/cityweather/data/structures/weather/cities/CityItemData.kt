package ua.sviatkuzbyt.cityweather.data.structures.weather.cities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.database.entities.CityEntity
import ua.sviatkuzbyt.cityweather.data.structures.weather.WeatherItemAppearance.Companion.getWeatherItemAppearance

@Stable
data class CityItemData(
    val cityId: Long,
    val name: String,
    val temperature: String,
    val windSpeed: String,
    @DrawableRes val image: Int,
    @StringRes val weatherDescription: Int,
    val background: CityBackground,
    val humidity: Int,
    val pressure: Int,
    val feelsLike: String,
    val rain: Int
) {
    companion object{
        fun map(entity: CityEntity): CityItemData{
            val appearance = getWeatherItemAppearance(entity.icon)
            return CityItemData(
                cityId = entity.cityId,
                name = entity.name,
                temperature = entity.temperature,
                windSpeed = entity.windSpeed,
                image = appearance.icon,
                weatherDescription = getEntityDescription(entity.icon),
                background = appearance.background,
                humidity = entity.humidity,
                pressure = entity.pressure,
                feelsLike = entity.feelsLike,
                rain = entity.rain
            )
        }

        private fun getEntityDescription(icon: String) = when (icon) {
            "01d", "01n" -> R.string.weather_clear
            "02d", "02n" -> R.string.weather_bit_cloudy
            "03d", "03n", "04d", "04n" -> R.string.weather_cloudy
            "09d", "09n" -> R.string.weather_rain
            "10d", "10n" -> R.string.weather_light_rain
            "11d", "11n" -> R.string.weather_thunderstorm
            "13d", "13n" -> R.string.weather_snow
            "50d", "50n" -> R.string.weather_foggy
            else -> R.string.weather_unknown
        }
    }
}

data class CityItemDetailData(
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
    val textContent: String
)