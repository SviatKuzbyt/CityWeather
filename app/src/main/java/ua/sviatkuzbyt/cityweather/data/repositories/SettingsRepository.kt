package ua.sviatkuzbyt.cityweather.data.repositories

import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.settingsstore.SettingsStoreManager
import ua.sviatkuzbyt.cityweather.data.structures.settings.SettingsItemData

private const val UNITS_ID = 1

class SettingsRepository(private val settingsStoreManager: SettingsStoreManager) {

    suspend fun getSettingsList() = listOf(
        SettingsItemData(
            id = UNITS_ID,
            label = R.string.units,
            setValue = settingsStoreManager.getUnits(),
            listOfValues = { listOf("standard", "metric", "imperial") }
        )
    )

    suspend fun setSettings(id: Int, value: String){
        when(id){
            UNITS_ID -> settingsStoreManager.setUnits(value)
        }
    }
}