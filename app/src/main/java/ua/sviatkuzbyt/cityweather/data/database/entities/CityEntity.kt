package ua.sviatkuzbyt.cityweather.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey(autoGenerate = true) val cityId: Long,
    val name: String,
    val temperature: String,
    val windSpeed: String,
    val icon: String,
    val humidity: Int,
    val pressure: Int,
    val feelsLike: String,
    val rain: Int,
    val time: Long,
    val units: String,
    val position: Int
)