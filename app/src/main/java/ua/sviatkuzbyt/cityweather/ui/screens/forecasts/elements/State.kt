package ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements

import androidx.annotation.StringRes
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastContent

sealed class ForecastState(){
    data object Loading: ForecastState()
    data class Content(val forecastContent: ForecastContent): ForecastState()
    data class Error(@StringRes val text: Int): ForecastState()
}