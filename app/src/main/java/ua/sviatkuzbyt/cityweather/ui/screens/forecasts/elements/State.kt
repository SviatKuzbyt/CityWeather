package ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements

import androidx.annotation.StringRes
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastData

enum class ForecastScreenType{
    Today, FiveDays
}

sealed class ForecastState(){
    data object Loading: ForecastState()
    data class Content(val forecastList: List<ForecastData>): ForecastState()
    data class Error(@StringRes val text: Int): ForecastState()
}