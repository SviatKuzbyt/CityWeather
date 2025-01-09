package ua.sviatkuzbyt.cityweather.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val position: Int
)
