package ua.sviatkuzbyt.cityweather.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DataBaseDao {
    @Query("SELECT * FROM city ORDER BY position")
    fun getCities(): List<CityEntity>

    @Query("SELECT * FROM city ORDER BY position")
    fun cities(): Flow<List<CityEntity>>

    @Update
    fun updateCity(city: CityEntity)

    @Insert
    fun addCity(cityEntity: CityEntity): Long

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
}