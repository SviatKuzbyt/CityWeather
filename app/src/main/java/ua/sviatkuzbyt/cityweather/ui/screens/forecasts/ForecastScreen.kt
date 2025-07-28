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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.repositories.ForecastFiveDaysRepository
import ua.sviatkuzbyt.cityweather.data.repositories.ForecastTodayRepository
import ua.sviatkuzbyt.cityweather.ui.LocalNavController
import ua.sviatkuzbyt.cityweather.ui.elements.basic.screens.LoadScreen
import ua.sviatkuzbyt.cityweather.ui.elements.basic.screens.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.basic.screens.TextPlug
import ua.sviatkuzbyt.cityweather.ui.elements.basic.topbar.LabelNavigateTopBar
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements.ForecastDescriptions
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements.ForecastItem
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements.ForecastScreenType
import ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements.ForecastState
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16

@Composable
fun ForecastTodayScreen(city: String) {
    ForecastScreen(city, ForecastScreenType.Today)
}

@Composable
fun ForecastFiveDaysScreen(city: String) {
    ForecastScreen(city, ForecastScreenType.FiveDays)
}

@Composable
private fun ForecastScreen(
    city: String,
    type: ForecastScreenType
){
    val context = LocalContext.current
    val viewModel: ForecastViewModel = viewModel(
        factory = ForecastViewModel.Factory(
            when(type){
                ForecastScreenType.Today -> ForecastTodayRepository(city, context)
                ForecastScreenType.FiveDays -> ForecastFiveDaysRepository(city, context)
            }
        )
    )
    val navController = LocalNavController.current

    val forecastState by viewModel.forecastState.collectAsState()

    val isWindSpeed = type == ForecastScreenType.Today
    val weatherWeight = if(isWindSpeed) 1f else 0.75f
    val title = when(type){
        ForecastScreenType.Today -> R.string.forecastToday
        ForecastScreenType.FiveDays -> R.string.forecastFiveDays
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
                    ForecastState.Loading -> LoadScreen()
                }
            }

        }
    )
}