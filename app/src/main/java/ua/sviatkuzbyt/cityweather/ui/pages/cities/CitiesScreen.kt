package ua.sviatkuzbyt.cityweather.ui.pages.cities

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.basic.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.cities.CitiesTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.cities.CityBackground
import ua.sviatkuzbyt.cityweather.ui.elements.cities.CityData
import ua.sviatkuzbyt.cityweather.ui.elements.cities.CityItem
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

@Preview
@Composable
fun CitiesScreen(){
    val testData = listOf(
        CityData("Test1", "+1°C", "1 m/s", R.drawable.weather_01d, R.string.weather_clear, CityBackground.BLue),
        CityData("Test2", "+2°C", "2 m/s", R.drawable.weather_02n, R.string.weather_clear, CityBackground.BlueDark),
        CityData("Test3", "+3°C", "3 m/s", R.drawable.weather_03, R.string.weather_cloudy, CityBackground.White),
        CityData("Test4", "+4°C", "4 m/s", R.drawable.weather_10n, R.string.weather_light_rain, CityBackground.WhiteDark),
        CityData("Test5", "+5°C", "5 m/s", R.drawable.weather_04, R.string.weather_heavy_clouds, CityBackground.Gray),
        CityData("Test6", "+6°C", "6 m/s", R.drawable.weather_11, R.string.weather_thunderstorm, CityBackground.GrayDark),
    )
    Screen(
        topBar = { CitiesTopBar() }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = spaceMedium)) {
            items(testData){
                CityItem(it)
            }
        }
    }
}