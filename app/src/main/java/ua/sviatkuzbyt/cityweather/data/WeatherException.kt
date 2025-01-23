package ua.sviatkuzbyt.cityweather.data

import android.util.Log
import ua.sviatkuzbyt.cityweather.R
import java.io.FileNotFoundException
import java.net.ConnectException
import java.net.UnknownHostException
import kotlin.Exception

class ExistCityException: Exception()

fun exceptionDescription(exception: Exception): Int {
    Log.e("SKLT", exception.toString())
    return when(exception){
        is UnknownHostException -> R.string.no_connect
        is ConnectException -> R.string.no_connect
        is FileNotFoundException -> R.string.no_info
        is ExistCityException -> R.string.city_already_added
        else -> R.string.error
    }
}