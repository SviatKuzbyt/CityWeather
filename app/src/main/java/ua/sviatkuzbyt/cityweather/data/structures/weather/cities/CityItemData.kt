package ua.sviatkuzbyt.cityweather.data.structures.weather.cities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.database.entities.CityEntity
import ua.sviatkuzbyt.cityweather.data.other.getWeatherDescription
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
                weatherDescription = getWeatherDescription(entity.icon),
                background = appearance.background,
                humidity = entity.humidity,
                pressure = entity.pressure,
                feelsLike = entity.feelsLike,
                rain = entity.rain
            )
        }
    }
}

data class CityItemDetailData(
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
    val textContent: String
)