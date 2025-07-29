package ua.sviatkuzbyt.cityweather.data.repositories

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import ua.sviatkuzbyt.cityweather.data.ExistCityException
import ua.sviatkuzbyt.cityweather.data.api.WeatherApi
import ua.sviatkuzbyt.cityweather.data.database.CityEntity
import ua.sviatkuzbyt.cityweather.data.database.DataBaseDao
import ua.sviatkuzbyt.cityweather.data.settingsstore.SettingsStoreManager
import ua.sviatkuzbyt.cityweather.data.structures.weather.UnitsData
import ua.sviatkuzbyt.cityweather.data.structures.weather.WeatherResponse

class CitiesRepository(
    private val dao: DataBaseDao,
    private val weatherApi: WeatherApi,
    private val settingsStoreManager: SettingsStoreManager
) {
    val cities = dao.cities()

    suspend fun loadData(){
        val units = UnitsData(settingsStoreManager.getUnits())

        cities.first().forEach { city ->
            val response = weatherApi.getCurrentWeather(city.name, units.unitsApi)
            val newEntity = mapWeatherResponseToCityEntity(city, response, units)
            dao.updateCity(newEntity)
        }
    }

    suspend fun addCity(name: String, position: Int) {
        val trimName = name.trim()
        if (dao.checkExistCity(trimName) == 0) {
            val units = UnitsData(settingsStoreManager.getUnits())
            val response = weatherApi.getCurrentWeather(trimName, units.unitsApi)
            val newEntity = mapWeatherResponseToCityEntity(trimName, position, response, units)
            dao.addCity(newEntity)
        } else {
            throw ExistCityException()
        }
    }

    fun deleteCity(id: Long, position: Int) {
        dao.moveCitiesUp(position)
        dao.deleteCity(id)
    }

    fun moveUpCity(id: Long, position: Int){
        dao.moveCitiesDown(position)
        dao.moveCityUp(id)
    }

    private fun mapWeatherResponseToCityEntity(
        entity: CityEntity,
        response: WeatherResponse,
        units: UnitsData
    ) = entity.copy(
        temperature = "${response.main.temp.toInt()}${units.temp}",
        windSpeed = "${response.wind.speed} ${units.wind}",
        icon = response.weather[0].icon,
        humidity = response.main.humidity,
        pressure = response.main.pressure,
        feelsLike = "${response.main.feelsLike.toInt()}${units.temp}",
        rain = response.rain?.percent?.toInt() ?: 0
    )

    private fun mapWeatherResponseToCityEntity(
        cityName: String,
        position: Int,
        response: WeatherResponse,
        units: UnitsData
    ) = CityEntity(
        cityId = 0,
        name = cityName,
        temperature = "${response.main.temp.toInt()}${units.temp}",
        windSpeed = "${response.wind.speed} ${units.wind}",
        icon = response.weather[0].icon,
        humidity = response.main.humidity,
        pressure = response.main.pressure,
        feelsLike = "${response.main.feelsLike.toInt()}${units.temp}",
        rain = response.rain?.percent?.toInt() ?: 0,
        position = position
    )
}