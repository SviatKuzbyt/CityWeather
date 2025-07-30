package ua.sviatkuzbyt.cityweather.data.structures.weather.forecast

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class ForecastData(
    val time: String,
    val temp: String,
    val windSpeed: String? = null,
    val weatherIcon: String,
)

enum class ForecastType { TODAY, FIVE_DAYS }

data class ForecastDataUI(
    val time: String,
    val temp: String,
    val windSpeed: String? = null,
    @DrawableRes val weatherIcon: Int,
    @StringRes val weatherDescription: Int
)

data class ForecastContent(
    val list: List<ForecastDataUI>,
    val isActualData: Boolean
)

data class Main(val temp: Float)