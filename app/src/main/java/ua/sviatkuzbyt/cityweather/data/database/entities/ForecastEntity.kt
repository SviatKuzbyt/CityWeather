package ua.sviatkuzbyt.cityweather.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastType

@Entity(
    tableName = "forecast",
    foreignKeys = [
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = ["cityId"],
            childColumns = ["cityId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true) val forecastId: Long = 0,
    val type: ForecastType,
    val data: List<ForecastData>,
    val time: Long,
    val units: String,
    val cityId: Long
)
