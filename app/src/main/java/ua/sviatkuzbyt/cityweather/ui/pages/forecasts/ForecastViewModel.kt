package ua.sviatkuzbyt.cityweather.ui.pages.forecasts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.cityweather.data.api.ForecastManager
import ua.sviatkuzbyt.cityweather.data.structures.ScreenState
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastData

class ForecastViewModel(private val repository: ForecastManager): ViewModel() {
    private val _forecastList = MutableStateFlow<List<ForecastData>>(listOf())
    private val _screenState = MutableStateFlow(ScreenState.Loading)

    val forecastList: StateFlow<List<ForecastData>> = _forecastList
    val screenState: StateFlow<ScreenState> = _screenState

    init {
        loadData()
    }

    fun loadData() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _screenState.value = ScreenState.Loading
            _forecastList.value = repository.loadForecast()
            _screenState.value = ScreenState.Content
        } catch (e: Exception){
            _screenState.value = ScreenState.Error
            Log.e("SKLT", e.toString())
        }
    }

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