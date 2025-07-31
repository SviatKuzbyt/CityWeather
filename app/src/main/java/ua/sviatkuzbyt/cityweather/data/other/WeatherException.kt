package ua.sviatkuzbyt.cityweather.data.other

import android.util.Log
import retrofit2.HttpException
import ua.sviatkuzbyt.cityweather.R
import java.net.ConnectException
import java.net.UnknownHostException

class ExistCityException: Exception()
class NoConnectException: Exception()

fun exceptionDescription(exception: Exception): Int {
    Log.e("SKLT", "Error caught", exception)
    return when(exception){
        is NoConnectException -> R.string.no_connect
        is UnknownHostException -> R.string.no_connect
        is ConnectException -> R.string.no_connect
        is HttpException -> {
            if (exception.code() >= 400 && exception.code() < 500) {
                R.string.incorrect_data
            } else {
                R.string.server_error
            }
        }
        is ExistCityException -> R.string.city_already_added
        else -> R.string.error
    }
}