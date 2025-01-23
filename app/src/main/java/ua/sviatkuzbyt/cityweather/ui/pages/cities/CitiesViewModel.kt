package ua.sviatkuzbyt.cityweather.ui.pages.cities

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.cityweather.data.exceptionDescription
import ua.sviatkuzbyt.cityweather.data.repositories.CitiesRepository
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.ScreenState

class CitiesViewModel(application: Application): AndroidViewModel(application) {
    private val repository = CitiesRepository(application)
    private val _cities = MutableStateFlow<List<CityItemData>>(listOf())
    private val _screenState = MutableStateFlow(ScreenState.Loading)
    private val _message = MutableStateFlow(0)

    val cities: StateFlow<List<CityItemData>> = _cities
    val screenState: StateFlow<ScreenState> = _screenState
    val message: StateFlow<Int> = _message

    init { loadCities() }

    private suspend fun saveableCall(code: suspend () -> Unit){
        try {
             code()
        } catch (e: Exception){
            _message.value = exceptionDescription(e)
        }
    }

    private fun loadCities() = viewModelScope.launch(Dispatchers.IO){
        saveableCall {
            _cities.value = repository.getCities()
            _screenState.value =
                if (_cities.value.isEmpty()) ScreenState.Empty
                else ScreenState.Content
        }
    }

    fun addCity(name: String) = viewModelScope.launch(Dispatchers.IO) {
        saveableCall {
            val position = _cities.value.size
            val newItem = repository.addCity(name, position)
            if (_cities.value.isEmpty()) _screenState.value = ScreenState.Content
            _cities.update { oldList ->
                oldList + newItem
            }
        }
    }

    fun deleteCity(id: Long, position: Int) = viewModelScope.launch(Dispatchers.IO) {
        saveableCall {
            repository.deleteCity(id, position)
            delay(500)
            _cities.update { oldList ->
                oldList - oldList[position]
            }
            if (_cities.value.isEmpty()) _screenState.value = ScreenState.Empty
        }
    }

    fun moveUpCity(id: Long, position: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.moveUpCity(id, position)
        val moveItem = _cities.value[position]

        delay(500)
        _cities.value -= moveItem
        _cities.value = listOf(moveItem) + _cities.value
    }
}