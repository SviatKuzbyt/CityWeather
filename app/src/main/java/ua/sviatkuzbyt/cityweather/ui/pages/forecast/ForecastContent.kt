package ua.sviatkuzbyt.cityweather.ui.pages.forecast

import androidx.annotation.StringRes
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ua.sviatkuzbyt.cityweather.data.structures.ScreenState
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.ui.LocalNavController
import ua.sviatkuzbyt.cityweather.ui.elements.basic.LabelNavigateTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.basic.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.forecast.ForecastDescriptions
import ua.sviatkuzbyt.cityweather.ui.elements.forecast.ForecastItem

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
        topBar = {
            LabelNavigateTopBar(
                text = stringResource(topBarLabel),
                onNavigate = { navController.navigateUp() }
            )
        },
        screenState = screenState,
        onErrorRetryClick = onErrorRetryClick,
        content = {
            item {
                ForecastDescriptions(isWindSpeed, weatherWeight)
            }

            items(forecastList){
                ForecastItem(it, weatherWeight)
            }
        }
    )
}