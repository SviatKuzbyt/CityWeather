package ua.sviatkuzbyt.cityweather.ui.pages.cities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ua.sviatkuzbyt.cityweather.data.repositories.CitiesRepository
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemData
import ua.sviatkuzbyt.cityweather.ui.elements.saveableCoroutineCall

class CitiesViewModel(application: Application): AndroidViewModel(application) {
    private val repository = CitiesRepository(application)

    private val _citiesList = MutableStateFlow<List<CityItemData>>(listOf())
    private val _isLoading = MutableStateFlow(false)
    private val _message = MutableStateFlow<Int?>(null)

    val citiesList: StateFlow<List<CityItemData>> = _citiesList
    val isLoading: StateFlow<Boolean> = _isLoading
    val message: StateFlow<Int?> = _message

    init { loadCities() }

    fun loadCities(){
        saveableCoroutineCall(
            code = {
                _isLoading.value = true
                _citiesList.value = repository.getWeatherForCities()
            },
            finallyHandler = {
                _isLoading.value = false
            },
            message = _message
        )
    }

    fun addCity(name: String)  {
        saveableCoroutineCall(
            code = {
                _isLoading.value = true
                val position = _citiesList.value.size
                val newItem = repository.addCity(name, position)
                _citiesList.update { oldList ->
                    oldList + newItem
                }
            },
            message = _message,
            finallyHandler = { _isLoading.value = false }
        )
    }

    fun deleteCity(id: Long, position: Int) {
        saveableCoroutineCall(_message) {
            repository.deleteCity(id, position)
            _citiesList.update { oldList ->
                oldList - oldList[position]
            }
        }
    }

    fun moveUpCity(id: Long, position: Int) {
        saveableCoroutineCall(_message) {
            repository.moveUpCity(id, position)
            val moveItem = _citiesList.value[position]
            _citiesList.value -= moveItem
            _citiesList.value = listOf(moveItem) + _citiesList.value
        }
    }

    fun clearMessage(){
        _message.value = null
    }
}