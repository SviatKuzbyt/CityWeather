package ua.sviatkuzbyt.cityweather.data.repositories

import android.content.Context
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import ua.sviatkuzbyt.cityweather.data.api.WeatherApi
import ua.sviatkuzbyt.cityweather.data.database.DataBaseDao
import ua.sviatkuzbyt.cityweather.data.database.entities.CityEntity
import ua.sviatkuzbyt.cityweather.data.other.ExistCityException
import ua.sviatkuzbyt.cityweather.data.other.canLoad
import ua.sviatkuzbyt.cityweather.data.other.runIfConnected
import ua.sviatkuzbyt.cityweather.data.structures.weather.UnitsData
import ua.sviatkuzbyt.cityweather.data.structures.weather.cities.WeatherResponse

class CitiesRepository(
    private val dao: DataBaseDao,
    private val weatherApi: WeatherApi,
    private val settingsRepository: SettingsRepository,
    private val context: Context
) {

    val cities = dao.cities().catch { emit(emptyList()) }

    suspend fun loadData() = runIfConnected(context) {
        val units = getCurrentUnits()
        cities.first().forEach { city ->
            if (canLoad(city.time, units.unitsApi, city.units)) {
                val response = weatherApi.getCurrentWeather(city.name, units.unitsApi)
                dao.updateCity(response.toCityEntity(city, units))
            }
        }
    }

    suspend fun addCity(name: String, position: Int) {
        val cityName = name.trim()
        if (dao.checkExistCity(cityName) > 0) throw ExistCityException()

        runIfConnected(context) {
            val units = getCurrentUnits()
            val response = weatherApi.getCurrentWeather(cityName, units.unitsApi)
            dao.addCity(response.toNewCityEntity(cityName, position, units))
        }
    }

    fun deleteCity(id: Long, position: Int) {
        dao.moveCitiesUp(position)
        dao.deleteCity(id)
    }

    fun moveUpCity(id: Long, position: Int) {
        dao.moveCitiesDown(position)
        dao.moveCityUp(id)
    }

    private suspend fun getCurrentUnits(): UnitsData =
        UnitsData(settingsRepository.getSettings(SettingsId.Units))

    private fun WeatherResponse.toCityEntity(
        existing: CityEntity,
        units: UnitsData
    ) = existing.copy(
        temperature = "${main.temp.toInt()}${units.temp}",
        windSpeed = "${wind.speed} ${units.wind}",
        icon = weather[0].icon,
        humidity = main.humidity,
        pressure = main.pressure,
        feelsLike = "${main.feelsLike.toInt()}${units.temp}",
        rain = rain?.percent?.toInt() ?: 0,
        time = System.currentTimeMillis(),
        units = units.unitsApi
    )

    private fun WeatherResponse.toNewCityEntity(
        name: String,
        position: Int,
        units: UnitsData
    ) = CityEntity(
        cityId = 0,
        name = name,
        temperature = "${main.temp.toInt()}${units.temp}",
        windSpeed = "${wind.speed} ${units.wind}",
        icon = weather[0].icon,
        humidity = main.humidity,
        pressure = main.pressure,
        feelsLike = "${main.feelsLike.toInt()}${units.temp}",
        rain = rain?.percent?.toInt() ?: 0,
        time = System.currentTimeMillis(),
        units = units.unitsApi,
        position = position
    )
}