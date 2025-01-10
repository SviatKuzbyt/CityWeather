package ua.sviatkuzbyt.cityweather.data.repositories

import android.content.Context
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.database.DataBaseManager
import ua.sviatkuzbyt.cityweather.data.structures.CityBackground
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData

class CitiesRepository(context: Context) {
    private val dataBaseDao = DataBaseManager.getDao(context)

    fun getCities(): List<CityItemData>{
        val cities = dataBaseDao.getCities()
        return cities.map {
            CityItemData(
                cityId = it.id,
                name = it.name,
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
        }
    }
}