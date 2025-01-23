package ua.sviatkuzbyt.cityweather.data.repositories

import android.content.Context
import ua.sviatkuzbyt.cityweather.data.api.CurrentWeatherManager
import ua.sviatkuzbyt.cityweather.data.database.CityEntity
import ua.sviatkuzbyt.cityweather.data.database.DataBaseManager
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData

class CitiesRepository(context: Context) {
    private val dataBaseDao = DataBaseManager.getDao(context)
    private val currentWeatherManager = CurrentWeatherManager()

    fun getCities(): List<CityItemData>{
        val cities = dataBaseDao.getCities()
        return cities.map {
            loadCity(it)
        }
    }

    fun addCity(name: String, position: Int): CityItemData{
        val cityEntity = CityEntity(0, name, position)
        val loadedWeather = loadCity(cityEntity)
        val addedId = dataBaseDao.addCity(cityEntity)

        return loadedWeather.copy(cityId = addedId)
    }

    //temp
    private fun loadCity(cityEntity: CityEntity): CityItemData {
        return currentWeatherManager.loadWeatherForCity(cityEntity)
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