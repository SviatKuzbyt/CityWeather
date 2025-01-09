package ua.sviatkuzbyt.cityweather.ui.pages.cities

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.CityBackground
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.cities.CitiesTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.CityItem

private const val NO_OPEN_ITEM = -1

@Preview
@Composable
fun CitiesScreen(){
    var openCityItem by rememberSaveable {
        mutableIntStateOf(NO_OPEN_ITEM)
    }
    val viewModel: CitiesViewModel = viewModel()
    val cities by viewModel.cities.collectAsState()

    Screen(
        topBar = { CitiesTopBar() }
    ) {
        itemsIndexed(cities){ index, item ->
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