package ua.sviatkuzbyt.cityweather.ui.pages.cities

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.basic.ScreenState
import ua.sviatkuzbyt.cityweather.ui.elements.cities.CitiesTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.CityItem

private const val NO_OPEN_ITEM = -1

@Preview
@Composable
fun CitiesScreen(){
    val viewModel: CitiesViewModel = viewModel()
    val cities by viewModel.cities.collectAsState()

    val screenState by viewModel.screenState.collectAsState()

    CitiesContent(
        cities = { cities },
        addCity = { name ->
            viewModel.addCity(name)
        },
        deleteCity = { id, position ->
            viewModel.deleteCity(id, position)
        },
        moveUpCity = {id, position ->
            viewModel.moveUpCity(id, position)
        },
        screenState = screenState
    )
}

@Composable
private fun CitiesContent(
    cities: () -> List<CityItemData>,
    addCity: (String) -> Unit,
    deleteCity: (Long, Int) -> Unit,
    moveUpCity: (Long, Int) -> Unit,
    screenState: ScreenState
){
    var openCityItem by rememberSaveable {
        mutableIntStateOf(NO_OPEN_ITEM)
    }

    Screen(
        screenState = screenState,
        emptyText = stringResource(R.string.no_cities),
        topBar = {
            CitiesTopBar(
                onAddCity = {city ->
                    addCity(city)
                }
            )
        },
        content = {
            itemsIndexed(cities.invoke()){ index, item ->
                val isOpen = openCityItem == index

                CityItem(
                    item,
                    isOpen,
                    onClickItem = {
                        openCityItem =
                            if (isOpen) NO_OPEN_ITEM
                            else index
                    },

                    onDelete = {
                        openCityItem = NO_OPEN_ITEM
                        deleteCity(item.cityId, index)
                    },

                    onMoveUp = {
                        if (index != 0) {
                            openCityItem = NO_OPEN_ITEM
                            moveUpCity(item.cityId, index)
                        }
                    }
                )
            }
        }
    )
}