package ua.sviatkuzbyt.cityweather.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastType

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromItemList(list: List<ForecastData>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toItemList(json: String): List<ForecastData>? {
        val type = object : TypeToken<List<ForecastData>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromForecastType(value: ForecastType): String = value.name

    @TypeConverter
    fun toForecastType(value: String): ForecastType = ForecastType.valueOf(value)
}