package ua.sviatkuzbyt.cityweather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.sviatkuzbyt.cityweather.data.database.entities.CityEntity

@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseRoom: RoomDatabase() {
    abstract fun dao(): DataBaseDao
}