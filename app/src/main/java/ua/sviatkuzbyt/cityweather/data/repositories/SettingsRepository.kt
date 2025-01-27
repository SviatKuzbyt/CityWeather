package ua.sviatkuzbyt.cityweather.data.repositories

import android.content.Context
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.settingsstore.SettingsStoreManager
import ua.sviatkuzbyt.cityweather.data.structures.settings.SettingsItemData

private const val UNITS_ID = 1

class SettingsRepository(private val context: Context) {

    suspend fun getSettingsList() = listOf(
        SettingsItemData(
            id = UNITS_ID,
            label = R.string.units,
            setValue = SettingsStoreManager.getUnits(context),
            listOfValues = { listOf("standard", "metric", "imperial") }
        )
    )

    suspend fun setSettings(id: Int, value: String){
        when(id){
            UNITS_ID -> SettingsStoreManager.setUnits(context, value)
        }
    }
}