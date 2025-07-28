package ua.sviatkuzbyt.cityweather.ui.screens.forecasts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ua.sviatkuzbyt.cityweather.data.api.ForecastManager
import ua.sviatkuzbyt.cityweather.data.exceptionDescription
import ua.sviatkuzbyt.cityweather.ui.elements.other.saveableCoroutineCall
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements.ForecastState

class ForecastViewModel(private val repository: ForecastManager): ViewModel() {
    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> = _forecastState

    init { loadData() }

    fun loadData() = saveableCoroutineCall(
        code = {
            _forecastState.value = ForecastState.Content(repository.loadForecast())
        },
        errorHandler = {
            _forecastState.value = ForecastState.Error(exceptionDescription(it))
        },
    )

    class Factory(private val repository: ForecastManager) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
                return ForecastViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}