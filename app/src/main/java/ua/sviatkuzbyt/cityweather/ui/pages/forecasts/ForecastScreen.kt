package ua.sviatkuzbyt.cityweather.ui.pages.forecasts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.repositories.ForecastFiveDaysRepository
import ua.sviatkuzbyt.cityweather.data.repositories.ForecastTodayRepository
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastScreenType
import ua.sviatkuzbyt.cityweather.ui.elements.forecast.ForecastContent

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

    val forecastList by viewModel.forecastList.collectAsState()
    val screenState by viewModel.screenState.collectAsState()

    ForecastContent(
        forecastList = forecastList,
        onErrorRetryClick = {viewModel.loadData()},
        screenState = screenState,
        isWindSpeed = type == ForecastScreenType.Today,
        topBarLabel = R.string.forecastToday
    )
}