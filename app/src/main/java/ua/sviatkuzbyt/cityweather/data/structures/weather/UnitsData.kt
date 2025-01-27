package ua.sviatkuzbyt.cityweather.data.structures.weather

data class UnitsData(
    val unitsApi: String,
    val temp: Char = getUnitsTemp(unitsApi),
    val wind: String = getUnitsWind(unitsApi)
){
    companion object{
        private fun getUnitsTemp(units: String) = when(units){
            "metric" -> '℃'
            "imperial" -> '℉'
            else -> 'K'
        }

        private fun getUnitsWind(units: String) =
            if(units == "imperial") "mi/h"
            else "m/s"
    }
}