package ua.sviatkuzbyt.cityweather.ui.pages.cities

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.sviatkuzbyt.cityweather.ui.elements.basic.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.cities.CitiesTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.cities.add.AddCitySheet
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.CityItem

private const val NO_OPEN_ITEM = -1

@Preview
@Composable
fun CitiesScreen(){
    var openCityItem by rememberSaveable {
        mutableIntStateOf(NO_OPEN_ITEM)
    }
    var showAddCitySheet by rememberSaveable {
        mutableStateOf(false)
    }

    val viewModel: CitiesViewModel = viewModel()
    val cities by viewModel.cities.collectAsState()

    AddCitySheet(
        isShow = showAddCitySheet,
        hideAction = {showAddCitySheet = false},
        addAction = { println("SKLT $it")}
    )

    Screen(
        topBar = {
            CitiesTopBar(
                onAddButtonClick = {
                    showAddCitySheet = true
                }
            )
        }
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