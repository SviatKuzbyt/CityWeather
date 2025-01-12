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
    val viewModel: CitiesViewModel = viewModel()

    var openCityItem by rememberSaveable {
        mutableIntStateOf(NO_OPEN_ITEM)
    }
    var showAddCitySheet by rememberSaveable {
        mutableStateOf(false)
    }

    val add: (String) -> Unit = {
        viewModel.addCity(it)
    }

    val delete: (Long, Int) -> Unit = { id, position ->
        viewModel.delete(id, position)
    }

    val moveUp: (Long, Int) -> Unit = { id, position ->
        viewModel.moveUpCity(id, position)
    }

    val cities by viewModel.cities.collectAsState()

    AddCitySheet(
        isShow = showAddCitySheet,
        hideAction = {
            showAddCitySheet = false
        },
        addAction = { city ->
            add(city)
        }
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
            CityItem(
                item,
                isOpen,
                onClickItem = {
                    openCityItem = if (isOpen){
                        NO_OPEN_ITEM
                    } else {
                        index
                    }
                },
                onDelete = {
                    openCityItem = NO_OPEN_ITEM
                    delete(item.cityId, index)
                },
                onMoveUp = {
                    if (index != 0) {
                        openCityItem = NO_OPEN_ITEM
                        moveUp(item.cityId, index)
                    }
                }
            )


        }
    }

}