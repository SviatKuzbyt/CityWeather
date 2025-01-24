package ua.sviatkuzbyt.cityweather.ui.pages.cities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.cityweather.data.exceptionDescription
import ua.sviatkuzbyt.cityweather.data.repositories.CitiesRepository
import ua.sviatkuzbyt.cityweather.data.structures.ScreenState
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemData

class CitiesViewModel(application: Application): AndroidViewModel(application) {
    private val repository = CitiesRepository(application)

    private val _cities = MutableStateFlow<List<CityItemData>>(listOf())
    private val _screenState = MutableStateFlow(ScreenState.Loading)
    private val _message = MutableStateFlow<Int?>(null)

    val cities: StateFlow<List<CityItemData>> = _cities
    val screenState: StateFlow<ScreenState> = _screenState
    val message: StateFlow<Int?> = _message

    init { loadCities() }

    private fun saveableCoroutineCall(
        errorHandler: () -> Unit = {},
        code: suspend () -> Unit
    ){
        viewModelScope.launch(Dispatchers.IO){
            try {
                code()
            } catch (e: Exception){
                _message.value = exceptionDescription(e)
                errorHandler()
            }
        }
    }

    fun loadCities(){
        saveableCoroutineCall(
            code = {
                _screenState.value = ScreenState.Loading
                _cities.value = repository.getWeatherForCities()
                _screenState.value =
                    if (_cities.value.isEmpty()) ScreenState.Empty
                    else ScreenState.Content
            },
            errorHandler = {
                _screenState.value = ScreenState.Error
            }
        )
    }

    fun addCity(name: String)  {
        saveableCoroutineCall {
            val position = _cities.value.size
            val newItem = repository.addCity(name, position)

            if (_cities.value.isEmpty()){
                _screenState.value = ScreenState.Content
            }

            _cities.update { oldList ->
                oldList + newItem
            }
        }
    }

    fun deleteCity(id: Long, position: Int) {
        saveableCoroutineCall {
            repository.deleteCity(id, position)
            _cities.update { oldList ->
                oldList - oldList[position]
            }
            if (_cities.value.isEmpty()) _screenState.value = ScreenState.Empty
        }
    }

    fun moveUpCity(id: Long, position: Int) {
        saveableCoroutineCall{
            repository.moveUpCity(id, position)
            val moveItem = _cities.value[position]
            _cities.value -= moveItem
            _cities.value = listOf(moveItem) + _cities.value
        }
    }

    fun clearMessage(){
        _message.value = null
    }
}