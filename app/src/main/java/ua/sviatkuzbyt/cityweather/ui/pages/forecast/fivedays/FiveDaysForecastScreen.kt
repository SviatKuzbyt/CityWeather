package ua.sviatkuzbyt.cityweather.ui.pages.forecast.fivedays

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.pages.forecast.ForecastContent

@Composable
fun FiveDaysForecastScreen(city: String){

    val viewModel: FiveDaysForecastViewModel = viewModel(
        factory = FiveDaysForecastViewModel.Factory(city)
    )

    val forecastList by viewModel.forecastList.collectAsState()
    val screenState by viewModel.screenState.collectAsState()

    ForecastContent(
        forecastList = forecastList,
        onErrorRetryClick = {viewModel.loadData()},
        screenState = screenState,
        isWindSpeed = false,
        topBarLabel = R.string.forecastToday
    )
}