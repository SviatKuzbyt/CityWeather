package ua.sviatkuzbyt.cityweather.ui.screens.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ua.sviatkuzbyt.cityweather.data.repositories.SettingsRepository
import ua.sviatkuzbyt.cityweather.data.structures.settings.SettingsItemData
import ua.sviatkuzbyt.cityweather.ui.elements.other.saveableCoroutineCall

class SettingsViewModel(private val repository: SettingsRepository): ViewModel() {

    private val _settingsList = MutableStateFlow<List<SettingsItemData>>(listOf())
    private val _message = MutableStateFlow<Int?>(null)

    val settingsList: StateFlow<List<SettingsItemData>> = _settingsList
    val message: StateFlow<Int?> = _message

    init { loadSettings() }

    private fun loadSettings(){
        saveableCoroutineCall(_message) {
            _settingsList.value = repository.getSettingsList()
        }
    }

    fun setSettings(id: Int, value: String){
        saveableCoroutineCall(_message) {
            _settingsList.value = _settingsList.value.map {settings ->
                if (settings.id == id){
                    settings.copy(setValue = value)
                } else {
                    settings
                }
            }
            repository.setSettings(id, value)
        }
    }

    fun clearMessage() {
        _message.value = null
    }
}