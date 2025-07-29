package ua.sviatkuzbyt.cityweather.ui.screens.cities

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ua.sviatkuzbyt.cityweather.data.repositories.CitiesRepository
import ua.sviatkuzbyt.cityweather.ui.elements.other.saveableCoroutineCall

class CitiesViewModel(private val repository: CitiesRepository): ViewModel() {

    val citiesList = repository.cities.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = listOf()
    )
    private val _isLoading = MutableStateFlow(false)
    private val _message = MutableStateFlow<Int?>(null)

    val isLoading: StateFlow<Boolean> = _isLoading
    val message: StateFlow<Int?> = _message

    init { loadCities() }

    fun loadCities(){
        saveableCoroutineCall(
            code = { repository.loadData() },
            errorHandler = {Log.e("SKLT", "", it)},
            finallyHandler = { _isLoading.value = false },
            message = _message
        )
    }

    fun addCity(name: String)  {
        saveableCoroutineCall(
            code = {
                val position = citiesList.value.size
                repository.addCity(name, position)
            },
            message = _message,
        )
    }

    fun deleteCity(id: Long, position: Int) {
//        saveableCoroutineCall(_message) {
//            repository.deleteCity(id, position)
//            _citiesList.update { oldList ->
//                oldList - oldList[position]
//            }
//        }
    }

    fun moveUpCity(id: Long, position: Int) {
//        saveableCoroutineCall(_message) {
//            repository.moveUpCity(id, position)
//            val moveItem = _citiesList.value[position]
//            _citiesList.value -= moveItem
//            _citiesList.value = listOf(moveItem) + _citiesList.value
//        }
    }

    fun clearMessage(){
        _message.value = null
    }
}