package ua.sviatkuzbyt.cityweather.ui.screens.forecasts

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastType
import ua.sviatkuzbyt.cityweather.ui.LocalNavController
import ua.sviatkuzbyt.cityweather.ui.elements.screens.LoadPlug
import ua.sviatkuzbyt.cityweather.ui.elements.screens.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.screens.TextPlug
import ua.sviatkuzbyt.cityweather.ui.elements.topbar.LabelNavigateTopBar
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements.ForecastDescriptions
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements.ForecastItem
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements.ForecastState
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16

@Composable
fun ForecastTodayScreen(cityId: Long) {
    ForecastScreen(cityId, ForecastType.TODAY)
}

@Composable
fun ForecastFiveDaysScreen(cityId: Long) {
    ForecastScreen(cityId, ForecastType.FIVE_DAYS)
}

@Composable
private fun ForecastScreen(
    cityId: Long,
    type: ForecastType
){
    val viewModel: ForecastViewModel = koinViewModel {
        parametersOf(type, cityId)
    }
    val navController = LocalNavController.current

    val forecastState by viewModel.forecastState.collectAsState()

    val isWindSpeed = type == ForecastType.TODAY
    val weatherWeight = if(isWindSpeed) 1f else 0.75f
    val title = when(type){
        ForecastType.TODAY -> R.string.forecastToday
        ForecastType.FIVE_DAYS -> R.string.forecastFiveDays
    }

    Screen(
        topBar = {
            LabelNavigateTopBar(
                text = stringResource(title),
                onNavigate = navController::navigateUp
            )
        },
        content = {
            Crossfade(forecastState) { state ->
                when(state) {
                    is ForecastState.Content -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = sizeSpace16)
                        ) {
                            item {
                                ForecastDescriptions(isWindSpeed, weatherWeight)
                            }
                            items(state.forecastList){
                                ForecastItem(it, weatherWeight)
                            }
                        }
                    }
                    is ForecastState.Error -> TextPlug(stringResource(state.text))
                    ForecastState.Loading -> LoadPlug()
                }
            }

        }
    )
}