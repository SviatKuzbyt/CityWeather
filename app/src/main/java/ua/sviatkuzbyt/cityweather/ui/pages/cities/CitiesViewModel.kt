package ua.sviatkuzbyt.cityweather.ui.pages.cities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.cityweather.data.repositories.CitiesRepository
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.ScreenState

class CitiesViewModel(application: Application): AndroidViewModel(application) {
    private val repository = CitiesRepository(application)
    private val _cities = MutableStateFlow<List<CityItemData>>(listOf())
    private val _screenState = MutableStateFlow(ScreenState.Loading)

    val cities: StateFlow<List<CityItemData>> = _cities
    val screenState: StateFlow<ScreenState> = _screenState

    init { loadCities() }

    private fun loadCities() = viewModelScope.launch(Dispatchers.IO){
        _cities.value = repository.getCities()
        _screenState.value =
            if (_cities.value.isEmpty()) ScreenState.Empty
            else ScreenState.Content
    }

    fun addCity(name: String) = viewModelScope.launch(Dispatchers.IO) {
        val position = _cities.value.size
        val newItem = repository.addCity(name, position)
        if (_cities.value.isEmpty()) _screenState.value = ScreenState.Content
        _cities.update { oldList ->
            oldList + newItem
        }
    }

    fun deleteCity(id: Long, position: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteCity(id, position)
        delay(500)
        _cities.update { oldList ->
            oldList - oldList[position]
        }
        if (_cities.value.isEmpty()) _screenState.value = ScreenState.Empty

    }

    fun moveUpCity(id: Long, position: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.moveUpCity(id, position)
        val moveItem = _cities.value[position]

        delay(500)
        _cities.value -= moveItem
        _cities.value = listOf(moveItem) + _cities.value
    }
}