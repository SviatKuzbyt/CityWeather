package ua.sviatkuzbyt.cityweather.data.structures.weather.forecast

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ForecastContent(
    val list: List<ForecastDataUI>,
    val isActualData: Boolean
)

data class ForecastDataUI(
    val time: String,
    val temp: String,
    val windSpeed: String? = null,
    @DrawableRes val weatherIcon: Int,
    @StringRes val weatherDescription: Int
)