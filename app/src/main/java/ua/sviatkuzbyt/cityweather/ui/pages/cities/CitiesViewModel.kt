package ua.sviatkuzbyt.cityweather.ui.pages.cities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.cityweather.data.repositories.CitiesRepository
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import kotlin.random.Random

class CitiesViewModel(application: Application): AndroidViewModel(application) {
    private val repository = CitiesRepository(application)
    private val _cities = MutableStateFlow<List<CityItemData>>(listOf())
    val cities: StateFlow<List<CityItemData>> = _cities

    init {
        viewModelScope.launch(Dispatchers.IO){
            _cities.value = repository.getCities()
        }
    }

    fun addCity(name: String) = viewModelScope.launch(Dispatchers.IO) {
        val position = _cities.value.size
        val newItem = repository.addCity(name, position)
        _cities.update { oldList ->
            oldList + newItem
        }
    }
}