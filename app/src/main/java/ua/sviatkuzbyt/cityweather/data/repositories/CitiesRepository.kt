package ua.sviatkuzbyt.cityweather.data.repositories

import android.content.Context
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.database.CityEntity
import ua.sviatkuzbyt.cityweather.data.database.DataBaseManager
import ua.sviatkuzbyt.cityweather.data.structures.CityBackground
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData

class CitiesRepository(context: Context) {
    private val dataBaseDao = DataBaseManager.getDao(context)

    fun getCities(): List<CityItemData>{
        val cities = dataBaseDao.getCities()
        return cities.map {
            loadCity(it)
        }
    }

    fun addCity(name: String, position: Int): CityItemData{
        val recordToAdd = CityEntity(0, name, position)
        val addedId = dataBaseDao.addCity(recordToAdd)
        val addedRecord = CityEntity(addedId, name, position)

        return loadCity(addedRecord)
    }

    //temp
    private fun loadCity(data: CityEntity) = CityItemData(
        cityId = data.id,
        name = data.name,
        temperature = "--°C",
        windSpeed = "-- m/s",
        image = R.drawable.unknown,
        weatherDescription = R.string.weather_clear,
        background = CityBackground.BLue,
        humidity = 0,
        pressure = "-- mm",
        feelsLike = "--°C",
        rain = 0
    )

    fun deleteCity(id: Long, position: Int) {
        dataBaseDao.moveCitiesUp(position)
        dataBaseDao.deleteCity(id)
    }

    fun moveUpCity(id: Long, position: Int){
        dataBaseDao.moveCitiesDown(position)
        dataBaseDao.moveCityUp(id)
    }
}