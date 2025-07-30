package ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements

import androidx.annotation.StringRes
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastDataUI

sealed class ForecastState(){
    data object Loading: ForecastState()
    data class Content(val forecastList: List<ForecastDataUI>): ForecastState()
    data class Error(@StringRes val text: Int): ForecastState()
}