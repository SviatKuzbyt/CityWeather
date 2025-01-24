package ua.sviatkuzbyt.cityweather.ui.pages.forecast.fivedays

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.cityweather.data.api.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.data.api.forecast.fivedays.ForecastFiveDaysManager
import ua.sviatkuzbyt.cityweather.ui.elements.basic.ScreenState

class FiveDaysForecastViewModel(private val city: String): ViewModel() {
    private val manager = ForecastFiveDaysManager()
    private val _forecastList = MutableStateFlow<List<ForecastData>>(listOf())
    private val _screenState = MutableStateFlow(ScreenState.Loading)

    val forecastList: StateFlow<List<ForecastData>> = _forecastList
    val screenState: StateFlow<ScreenState> = _screenState

    init {
        loadData()
    }

    fun loadData() = viewModelScope.launch(Dispatchers.IO) {
        try {
            delay(300)
            _screenState.value = ScreenState.Loading
            _forecastList.value = manager.loadForecast(city)
            _screenState.value = ScreenState.Content
        } catch (e: Exception){
            _screenState.value = ScreenState.Error
            Log.e("SKLT", e.toString())
        }
    }

    class Factory(private val city: String) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FiveDaysForecastViewModel::class.java)) {
                return FiveDaysForecastViewModel(city) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}