package ua.sviatkuzbyt.cityweather.ui.screens.forecasts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ua.sviatkuzbyt.cityweather.data.exceptionDescription
import ua.sviatkuzbyt.cityweather.data.repositories.ForecastRepository
import ua.sviatkuzbyt.cityweather.ui.elements.other.saveableCoroutineCall
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements.ForecastScreenType
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements.ForecastState

class ForecastViewModel(
    private val repository: ForecastRepository,
    private val type: ForecastScreenType,
    private val city: String
): ViewModel() {

    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> = _forecastState

    init { loadData() }

    fun loadData() = saveableCoroutineCall(
        code = {
            val list = when(type){
                ForecastScreenType.Today -> repository.loadForecastToday(city)
                ForecastScreenType.FiveDays -> repository.loadForecastFiveDays(city)
            }
            _forecastState.value = ForecastState.Content(list)
        },
        errorHandler = {
            _forecastState.value = ForecastState.Error(exceptionDescription(it))
        },
    )
}