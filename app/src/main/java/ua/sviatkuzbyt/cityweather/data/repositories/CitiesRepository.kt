package ua.sviatkuzbyt.cityweather.data.repositories

import android.content.Context
import ua.sviatkuzbyt.cityweather.data.ExistCityException
import ua.sviatkuzbyt.cityweather.data.api.CurrentWeatherManager
import ua.sviatkuzbyt.cityweather.data.database.CityEntity
import ua.sviatkuzbyt.cityweather.data.database.DataBaseManager
import ua.sviatkuzbyt.cityweather.data.settingsstore.SettingsStoreManager
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemData
import ua.sviatkuzbyt.cityweather.data.structures.weather.UnitsData

private const val NO_EXIST = 0

class CitiesRepository(private val context: Context) {
    private val dataBaseDao = DataBaseManager.getDao(context)
    private val currentWeatherManager = CurrentWeatherManager()

    suspend fun getWeatherForCities(): List<CityItemData>{
        val units = UnitsData(SettingsStoreManager.getUnits(context))
        val cities = dataBaseDao.getCities()
        return cities.map {
            loadWeather(it, units)
        }
    }

    suspend fun addCity(name: String, position: Int): CityItemData {
        val nameTrim = name.trim()

        if (dataBaseDao.checkExistCity(nameTrim) == NO_EXIST){
            val units = UnitsData(SettingsStoreManager.getUnits(context))
            val cityEntity = CityEntity(0, name.trim(), position)
            val loadedWeather = loadWeather(cityEntity, units)
            val addedId = dataBaseDao.addCity(cityEntity)

            return loadedWeather.copy(cityId = addedId)
        } else{
            throw ExistCityException()
        }
    }

    private fun loadWeather(cityEntity: CityEntity, units: UnitsData): CityItemData {
        return currentWeatherManager.loadWeatherForCity(cityEntity, units)
    }

    fun deleteCity(id: Long, position: Int) {
        dataBaseDao.moveCitiesUp(position)
        dataBaseDao.deleteCity(id)
    }

    fun moveUpCity(id: Long, position: Int){
        dataBaseDao.moveCitiesDown(position)
        dataBaseDao.moveCityUp(id)
    }
}