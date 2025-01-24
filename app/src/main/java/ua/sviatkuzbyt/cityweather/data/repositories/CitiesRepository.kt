package ua.sviatkuzbyt.cityweather.data.repositories

import android.content.Context
import ua.sviatkuzbyt.cityweather.data.ExistCityException
import ua.sviatkuzbyt.cityweather.data.api.CurrentWeatherManager
import ua.sviatkuzbyt.cityweather.data.database.CityEntity
import ua.sviatkuzbyt.cityweather.data.database.DataBaseManager
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemData

private const val NO_EXIST = 0

class CitiesRepository(context: Context) {
    private val dataBaseDao = DataBaseManager.getDao(context)
    private val currentWeatherManager = CurrentWeatherManager()

    fun getWeatherForCities(): List<CityItemData>{
        val cities = dataBaseDao.getCities()
        return cities.map {
            loadWeather(it)
        }
    }

    fun addCity(name: String, position: Int): CityItemData {
        val nameTrim = name.trim()

        if (dataBaseDao.checkExistCity(nameTrim) == NO_EXIST){
            val cityEntity = CityEntity(0, name.trim(), position)
            val loadedWeather = loadWeather(cityEntity)
            val addedId = dataBaseDao.addCity(cityEntity)

            return loadedWeather.copy(cityId = addedId)
        } else{
            throw ExistCityException()
        }
    }

    private fun loadWeather(cityEntity: CityEntity): CityItemData {
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