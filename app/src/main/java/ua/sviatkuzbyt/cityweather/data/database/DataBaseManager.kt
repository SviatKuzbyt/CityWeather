package ua.sviatkuzbyt.cityweather.data.database

import android.content.Context
import androidx.room.Room

object DataBaseManager {
    private var dataBase: DatabaseRoom? = null

    fun getDao(context: Context) = getDatabase(context).dao()

    private fun getDatabase(context: Context): DatabaseRoom {
        if (dataBase == null) {
            synchronized(DataBaseManager::class.java) {
                if (dataBase == null) {
                    dataBase = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseRoom::class.java,
                        "city_weather_db"
                    ).build()
                }
            }
        }
        return dataBase!!
    }
}