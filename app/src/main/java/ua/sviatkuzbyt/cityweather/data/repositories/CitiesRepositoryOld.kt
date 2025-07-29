package ua.sviatkuzbyt.cityweather.data.repositories

//import ua.sviatkuzbyt.cityweather.data.ExistCityException
//import ua.sviatkuzbyt.cityweather.data.api.CurrentWeatherManager
//import ua.sviatkuzbyt.cityweather.data.database.CityEntity
//import ua.sviatkuzbyt.cityweather.data.database.DataBaseDao
//import ua.sviatkuzbyt.cityweather.data.settingsstore.SettingsStoreManager
//import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemData
//import ua.sviatkuzbyt.cityweather.data.structures.weather.UnitsData
//
//private const val NO_EXIST = 0
//
//class CitiesRepositoryOld(
//    private val dao: DataBaseDao,
//    private val currentWeatherManager: CurrentWeatherManager,
//    private val settingsStoreManager: SettingsStoreManager
//) {
//
//    suspend fun getWeatherForCities(): List<CityItemData>{
//        val units = UnitsData(settingsStoreManager.getUnits())
//        val cities = dao.getCities()
//        return cities.map {
//            loadWeather(it, units)
//        }
//    }
//
//    suspend fun addCity(name: String, position: Int): CityItemData {
//        val nameTrim = name.trim()
//
//        if (dao.checkExistCity(nameTrim) == NO_EXIST){
//            val units = UnitsData(settingsStoreManager.getUnits())
//            val cityEntity = CityEntity(0, name.trim(), position)
//            val loadedWeather = loadWeather(cityEntity, units)
//            val addedId = dao.addCity(cityEntity)
//
//            return loadedWeather.copy(cityId = addedId)
//        } else{
//            throw ExistCityException()
//        }
//    }
//
//    private fun loadWeather(cityEntity: CityEntity, units: UnitsData): CityItemData {
//        return currentWeatherManager.loadWeatherForCity(cityEntity, units)
//    }
//
//    fun deleteCity(id: Long, position: Int) {
//        dao.moveCitiesUp(position)
//        dao.deleteCity(id)
//    }
//
//    fun moveUpCity(id: Long, position: Int){
//        dao.moveCitiesDown(position)
//        dao.moveCityUp(id)
//    }
//}