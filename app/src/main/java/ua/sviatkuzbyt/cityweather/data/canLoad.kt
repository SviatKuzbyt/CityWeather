package ua.sviatkuzbyt.cityweather.data

fun canLoad(time: Long, currentUnits: String, lastUnits: String): Boolean{
    val currentTime = System.currentTimeMillis()

    return if ((currentTime - time) >= 30 * 60 * 1000) true
    else if (currentUnits != lastUnits) true
    else false
}