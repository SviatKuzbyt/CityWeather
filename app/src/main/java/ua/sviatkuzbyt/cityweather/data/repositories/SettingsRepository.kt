package ua.sviatkuzbyt.cityweather.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.settings.SettingsItemData

private val Context.settingsStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

enum class SettingsId{ Units }

class SettingsRepository(private val context: Context) {
    private val unitsKey = stringPreferencesKey("units")

    suspend fun getSettingsInList() = listOf(
        SettingsItemData(
            id = SettingsId.Units,
            label = R.string.units,
            setValue = getData(unitsKey, "metric"),
            listOfValues = { listOf("standard", "metric", "imperial") }
        )
    )

    suspend fun setSettings(id: SettingsId, value: String){
        when(id){
            SettingsId.Units -> setData(unitsKey, value)
        }
    }

    suspend fun getSettings(id: SettingsId): String {
        return when(id){
            SettingsId.Units -> getData(unitsKey, "metric")
        }
    }

    private suspend fun <T> getData(
        key: Preferences.Key<T>,
        default: T
    ): T {
        return context.settingsStore.data
            .map { it[key] ?: default }
            .first()
    }

    private suspend fun <T>setData(key: Preferences.Key<T>, value: T){
        context.settingsStore.edit {
            it[key] = value
        }
    }
}