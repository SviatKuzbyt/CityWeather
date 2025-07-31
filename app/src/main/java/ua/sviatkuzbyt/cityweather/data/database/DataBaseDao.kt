package ua.sviatkuzbyt.cityweather.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ua.sviatkuzbyt.cityweather.data.database.entities.CityEntity
import ua.sviatkuzbyt.cityweather.data.database.entities.ForecastEntity
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastType

@Dao
interface DataBaseDao {
    @Query("SELECT * FROM city ORDER BY position")
    fun cities(): Flow<List<CityEntity>>

    @Update
    fun updateCity(city: CityEntity)

    @Insert
    fun addCity(cityEntity: CityEntity)

    @Query("UPDATE city SET position=position+1 WHERE position < :toPosition")
    fun moveCitiesDown(toPosition: Int)

    @Query("UPDATE city SET position=0 WHERE cityId = :updateId")
    fun moveCityUp(updateId: Long)

    @Query("UPDATE city SET position=position-1 WHERE position > :fromPosition")
    fun moveCitiesUp(fromPosition: Int)

    @Query("DELETE FROM city WHERE cityId=:id")
    fun deleteCity(id: Long)

    @Query("SELECT 1 FROM city WHERE name=:name")
    fun checkExistCity(name: String): Int

    @Query("SELECT * FROM forecast WHERE type = :type AND cityId = :cityId LIMIT 1")
    fun getForecast(cityId: Long, type: ForecastType): ForecastEntity?

    @Update
    fun updateForecast(forecast: ForecastEntity)

    @Insert
    fun addForecast(forecast: ForecastEntity)

    @Query("SELECT name FROM city WHERE cityId=:cityId LIMIT 1")
    fun getCityName(cityId: Long): String
}