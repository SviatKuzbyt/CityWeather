package ua.sviatkuzbyt.cityweather.data.settingsstore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.settingsStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsStoreManager(private val context: Context){

    private val unitsKey = stringPreferencesKey("units")

    suspend fun getUnits() =
        context.settingsStore.data.map {
            it[unitsKey] ?: "metric"
        }.first()

    suspend fun setUnits(value: String){
        context.settingsStore.edit {
            it[unitsKey] = value
        }
    }
}