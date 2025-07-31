package ua.sviatkuzbyt.cityweather.ui.screens.forecasts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ua.sviatkuzbyt.cityweather.data.other.exceptionDescription
import ua.sviatkuzbyt.cityweather.data.repositories.ForecastRepository
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastType
import ua.sviatkuzbyt.cityweather.ui.elements.other.saveableCoroutineCall
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements.ForecastState

class ForecastViewModel(
    private val repository: ForecastRepository,
    private val type: ForecastType,
    private val cityId: Long
): ViewModel() {

    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> = _forecastState

    init { loadData() }

    fun loadData() = saveableCoroutineCall(
        code = {
            val list = repository.loadForecast(cityId, type)
            _forecastState.value = ForecastState.Content(list)
        },
        errorHandler = {
            _forecastState.value = ForecastState.Error(exceptionDescription(it))
        },
    )
}