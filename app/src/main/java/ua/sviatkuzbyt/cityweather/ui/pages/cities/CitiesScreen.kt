package ua.sviatkuzbyt.cityweather.ui.pages.cities

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.CityBackground
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.cities.CitiesTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.CityItem

private const val NO_OPEN_ITEM = -1

private val testData = listOf(
    CityItemData(1, "Test1", "+1°C", "1 m/s", R.drawable.weather_01d, R.string.weather_clear, CityBackground.BLue, 1, "1 mm", "+1°C", 1),
    CityItemData(2, "Test2", "+2°C", "2 m/s", R.drawable.weather_02n, R.string.weather_clear, CityBackground.BlueDark, 2, "2 mm", "+2°C", 2),
    CityItemData(3, "Test3", "+3°C", "3 m/s", R.drawable.weather_03, R.string.weather_cloudy, CityBackground.White, 3, "3 mm", "+3°C", 3),
    CityItemData(4, "Test4", "+4°C", "4 m/s", R.drawable.weather_10n, R.string.weather_light_rain, CityBackground.WhiteDark, 4, "4 mm", "+4°C", 4),
    CityItemData(5, "Test5", "+5°C", "5 m/s", R.drawable.weather_04, R.string.weather_heavy_clouds, CityBackground.Gray, 5, "5 mm", "+5°C", 5),
    CityItemData(6, "Test6", "+6°C", "6 m/s", R.drawable.weather_11, R.string.weather_thunderstorm, CityBackground.GrayDark, 6, "6 mm", "+6°C", 6),
)

@Preview
@Composable
fun CitiesScreen(){
    var openCityItem by rememberSaveable {
        mutableIntStateOf(NO_OPEN_ITEM)
    }

    Screen(
        topBar = { CitiesTopBar() }
    ) {
        itemsIndexed(testData){ index, item ->
            val isOpen = openCityItem == index
            CityItem(item, isOpen) {
                openCityItem = if (isOpen){
                    NO_OPEN_ITEM
                } else {
                    index
                }
            }
        }
    }
}