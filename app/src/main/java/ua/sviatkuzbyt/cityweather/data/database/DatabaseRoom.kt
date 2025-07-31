package ua.sviatkuzbyt.cityweather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ua.sviatkuzbyt.cityweather.data.database.entities.CityEntity
import ua.sviatkuzbyt.cityweather.data.database.entities.ForecastEntity

@Database(
    entities = [CityEntity::class, ForecastEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DatabaseRoom: RoomDatabase() {
    abstract fun dao(): DataBaseDao
}