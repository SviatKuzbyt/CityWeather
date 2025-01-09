package ua.sviatkuzbyt.cityweather.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataBaseDao {
    @Query("SELECT * FROM city ORDER BY position")
    fun getCities(): List<CityEntity>

    @Insert
    fun addCity(cityEntity: CityEntity)

    @Query("UPDATE city SET position=position+1 WHERE position < :toPosition")
    fun moveCitiesDown(toPosition: Int)

    @Query("UPDATE city SET position=0 WHERE id = :updateId")
    fun moveCityUp(updateId: Long)

    @Query("UPDATE city SET position=position-1 WHERE position > :fromPosition")
    fun moveCitiesUp(fromPosition: Int)

    @Query("DELETE FROM city WHERE id=:id")
    fun deleteCity(id: Long)
}