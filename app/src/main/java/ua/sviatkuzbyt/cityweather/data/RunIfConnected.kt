package ua.sviatkuzbyt.cityweather.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

suspend fun runIfConnected(context: Context, code: suspend () -> Unit){
    if (isConnected(context)) code()
    else throw NoConnectException()
}

private fun isConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = cm.activeNetwork ?: return false
    val capabilities = cm.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
}