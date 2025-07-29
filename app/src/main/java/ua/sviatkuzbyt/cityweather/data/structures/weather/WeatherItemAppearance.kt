package ua.sviatkuzbyt.cityweather.data.structures.weather

import androidx.annotation.DrawableRes
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.weather.cities.CityBackground

data class WeatherItemAppearance(
    @DrawableRes val icon: Int,
    val background: CityBackground
) {
    companion object{
        private val items = mapOf(
            "01d" to WeatherItemAppearance(R.drawable.weather_01d, CityBackground.BLue),
            "01n" to WeatherItemAppearance(R.drawable.weather_01n, CityBackground.BlueDark),
            "02d" to WeatherItemAppearance(R.drawable.weather_02d, CityBackground.BLue),
            "02n" to WeatherItemAppearance(R.drawable.weather_02n, CityBackground.BlueDark),
            "03d" to WeatherItemAppearance(R.drawable.weather_03, CityBackground.White),
            "03n" to WeatherItemAppearance(R.drawable.weather_03, CityBackground.WhiteDark),
            "04d" to WeatherItemAppearance(R.drawable.weather_04, CityBackground.Gray),
            "04n" to WeatherItemAppearance(R.drawable.weather_04, CityBackground.GrayDark),
            "09d" to WeatherItemAppearance(R.drawable.weather_09, CityBackground.Gray),
            "09n" to WeatherItemAppearance(R.drawable.weather_09, CityBackground.GrayDark),
            "10d" to WeatherItemAppearance(R.drawable.weather_10d, CityBackground.White),
            "10n" to WeatherItemAppearance(R.drawable.weather_10n, CityBackground.WhiteDark),
            "11d" to WeatherItemAppearance(R.drawable.weather_11, CityBackground.Gray),
            "11n" to WeatherItemAppearance(R.drawable.weather_11, CityBackground.GrayDark),
            "13d" to WeatherItemAppearance(R.drawable.weather_13, CityBackground.White),
            "13n" to WeatherItemAppearance(R.drawable.weather_13, CityBackground.WhiteDark),
            "50d" to WeatherItemAppearance(R.drawable.weather_50, CityBackground.White),
            "50n" to WeatherItemAppearance(R.drawable.weather_50, CityBackground.WhiteDark)
        )

        fun getWeatherItemAppearance(code: String): WeatherItemAppearance {
            return items[code]
                ?: WeatherItemAppearance(R.drawable.unknown, CityBackground.BLue)
        }
    }
}