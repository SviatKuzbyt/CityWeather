@file:OptIn(ExperimentalMaterial3Api::class)

package ua.sviatkuzbyt.cityweather.ui.pages.cities

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.ScreenState
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemData
import ua.sviatkuzbyt.cityweather.ui.ForecastFiveDaysRoute
import ua.sviatkuzbyt.cityweather.ui.ForecastTodayRoute
import ua.sviatkuzbyt.cityweather.ui.LocalNavController
import ua.sviatkuzbyt.cityweather.ui.elements.basic.screens.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.cities.CitiesTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.CityItem
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.ToastMessage
import ua.sviatkuzbyt.cityweather.ui.elements.cities.RefreshLazyColumn

private const val NO_OPEN_ITEM = -1

@Preview
@Composable
fun CitiesScreen(){
    val viewModel: CitiesViewModel = viewModel()
    val navController = LocalNavController.current

    val cities by viewModel.cities.collectAsState()
    val screenState by viewModel.screenState.collectAsState()

    val message by viewModel.message.collectAsStateWithLifecycle(
        minActiveState = Lifecycle.State.RESUMED
    )

    CitiesContent(
        screenState = screenState,
        message = message,
        cities = {
            cities
        },
        addCity = { name ->
            viewModel.addCity(name)
        },
        deleteCity = { id, position ->
            viewModel.deleteCity(id, position)
        },
        moveUpCity = {id, position ->
            viewModel.moveUpCity(id, position)
        },
        clearMessage = {
            viewModel.clearMessage()
        },
        errorReturn = {
            viewModel.loadCities()
        },
        openForecastToday = { city ->
            navController.navigate(ForecastTodayRoute(city))
        },
        openForecastFiveDays = { city ->
            navController.navigate(ForecastFiveDaysRoute(city))
        },
        update = {
            viewModel.loadCities()
        }
    )
}

@Composable
private fun CitiesContent(
    cities: () -> List<CityItemData>,
    addCity: (String) -> Unit,
    deleteCity: (Long, Int) -> Unit,
    moveUpCity: (Long, Int) -> Unit,
    screenState: ScreenState,
    message: Int?,
    clearMessage: () -> Unit,
    errorReturn: () -> Unit,
    openForecastToday: (String) -> Unit,
    openForecastFiveDays: (String) -> Unit,
    update: () -> Unit
){
    var openCityItem by rememberSaveable {
        mutableIntStateOf(NO_OPEN_ITEM)
    }

    ToastMessage(message, clearMessage)

    Screen(
        screenState = screenState,
        emptyText = stringResource(R.string.no_cities),
        onErrorRetryClick = errorReturn,

        topBar = {
            CitiesTopBar(
                onAddCity = {city ->
                    addCity(city)
                },
                onMoreButtonClick = {}
            )
        },

        content = {
            RefreshLazyColumn(update){
                itemsIndexed(cities.invoke()){ index, item ->
                    val isOpen = openCityItem == index

                    CityItem(
                        data = item,
                        isOpen = isOpen,
                        onTodayClick = openForecastToday,
                        onFiveDaysClick = openForecastFiveDays,
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
        }
    )
}