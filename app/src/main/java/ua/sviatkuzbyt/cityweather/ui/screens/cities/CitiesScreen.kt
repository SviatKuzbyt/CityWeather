package ua.sviatkuzbyt.cityweather.ui.screens.cities

import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.ForecastFiveDaysRoute
import ua.sviatkuzbyt.cityweather.ui.ForecastTodayRoute
import ua.sviatkuzbyt.cityweather.ui.LocalNavController
import ua.sviatkuzbyt.cityweather.ui.SettingsRoute
import ua.sviatkuzbyt.cityweather.ui.elements.screens.Screen
import ua.sviatkuzbyt.cityweather.ui.screens.cities.elements.CitiesTopBar
import ua.sviatkuzbyt.cityweather.ui.screens.cities.elements.list.CityItem
import ua.sviatkuzbyt.cityweather.ui.elements.other.ToastMessage
import ua.sviatkuzbyt.cityweather.ui.elements.other.RefreshLazyColumn
import ua.sviatkuzbyt.cityweather.ui.elements.screens.TextPlug
import ua.sviatkuzbyt.cityweather.ui.elements.screens.TextPlugList
import ua.sviatkuzbyt.cityweather.ui.screens.cities.elements.AddCityDialog

private const val NO_OPEN_ITEM = -1

@Preview
@Composable
fun CitiesScreen(){
    val viewModel: CitiesViewModel = viewModel()
    val navController = LocalNavController.current

    val citiesList by viewModel.citiesList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val message by viewModel.message.collectAsStateWithLifecycle(
        minActiveState = Lifecycle.State.RESUMED
    )

    var openCityItem by rememberSaveable {
        mutableIntStateOf(NO_OPEN_ITEM)
    }
    var isAddDialogOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Screen(
        topBar = {
            CitiesTopBar(
                onAddCityClick = { isAddDialogOpen = true},
                onMoreButtonClick = { navController.navigate(SettingsRoute) }
            )
        },
        content = {
            RefreshLazyColumn(
                isRefreshing = isLoading,
                onRefresh = { viewModel.loadCities() },
                content = {
                    if (citiesList.isEmpty() && !isLoading) {
                        item { TextPlugList(stringResource(R.string.no_cities)) }
                    } else {
                        itemsIndexed(citiesList){ index, city ->
                            val isOpen = openCityItem == index

                            CityItem(
                                data = city,
                                isOpen = isOpen,
                                onTodayClick = {
                                    navController.navigate(ForecastTodayRoute(city.name))
                                },
                                onFiveDaysClick = {
                                    navController.navigate(ForecastFiveDaysRoute(city.name))
                                },
                                onClickItem = {
                                    openCityItem =
                                        if (isOpen) NO_OPEN_ITEM
                                        else index
                                },
                                onDelete = {
                                    openCityItem = NO_OPEN_ITEM
                                    viewModel.deleteCity(city.cityId, index)
                                },
                                onMoveUp = {
                                    if (index != 0) {
                                        openCityItem = NO_OPEN_ITEM
                                        viewModel.moveUpCity(city.cityId, index)
                                    }
                                }
                            )
                        }
                    }
                }
            )
        }
    )

    if (isAddDialogOpen){
        AddCityDialog(
            hideAction = { isAddDialogOpen = false },
            addAction = { cityName -> viewModel.addCity(cityName) }
        )
    }

    ToastMessage(message, viewModel::clearMessage)
}