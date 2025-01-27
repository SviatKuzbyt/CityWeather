package ua.sviatkuzbyt.cityweather.ui.elements.forecast

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ua.sviatkuzbyt.cityweather.data.structures.ScreenState
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.ui.LocalNavController
import ua.sviatkuzbyt.cityweather.ui.elements.basic.topbar.LabelNavigateTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.basic.screens.Screen

@Composable
fun ForecastContent(
    forecastList: List<ForecastData>,
    onErrorRetryClick: () -> Unit,
    screenState: ScreenState,
    isWindSpeed: Boolean,
    @StringRes topBarLabel: Int
){
    val navController = LocalNavController.current
    val weatherWeight = if(isWindSpeed) 1f else 0.75f

    Screen(
        screenState = screenState,
        onErrorRetryClick = onErrorRetryClick,
        topBar = {
            LabelNavigateTopBar(
                text = stringResource(topBarLabel),
                onNavigate = { navController.navigateUp() }
            )
        },
        content = {
            LazyColumn(Modifier.fillMaxSize()) {
                item {
                    ForecastDescriptions(isWindSpeed, weatherWeight)
                }
                items(forecastList){
                    ForecastItem(it, weatherWeight)
                }
            }
        }
    )
}