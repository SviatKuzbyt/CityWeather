package ua.sviatkuzbyt.cityweather.data.other

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

suspend fun runIfConnected(context: Context, code: suspend () -> Unit){
    if (isConnected(context)) code()
    else throw NoConnectException()
}

fun isConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = cm.activeNetwork ?: return false
    val capabilities = cm.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
}

fun canLoad(time: Long, currentUnits: String, lastUnits: String): Boolean{
    val currentTime = System.currentTimeMillis()

    return if ((currentTime - time) >= 30 * 60 * 1000) true
    else if (currentUnits != lastUnits) true
    else false
}