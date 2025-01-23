package ua.sviatkuzbyt.cityweather.data

import android.util.Log
import ua.sviatkuzbyt.cityweather.R
import java.io.FileNotFoundException
import java.lang.Exception
import java.net.UnknownHostException

fun exceptionDescription(exception: Exception): Int {
    Log.e("SKLT", exception.toString())
    return when(exception){
        is UnknownHostException -> R.string.no_connect
        is FileNotFoundException -> R.string.no_info
        else -> R.string.error
    }
}