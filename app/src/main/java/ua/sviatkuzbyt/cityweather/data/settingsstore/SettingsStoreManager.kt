package ua.sviatkuzbyt.cityweather.data.settingsstore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object SettingsStoreManager{
    private val Context.settingsStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val unitsKey = stringPreferencesKey("units")

    suspend fun getUnits(context: Context) =
        context.settingsStore.data.map {
            it[unitsKey] ?: "standard"
        }.first()

    suspend fun setUnits(context: Context, value: String){
        context.settingsStore.edit {
            it[unitsKey] = value
        }
    }
}