package ua.sviatkuzbyt.cityweather.ui.pages.forecast.today

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.pages.forecast.ForecastContent

@Composable
fun TodayForecastScreen(city: String){

    val viewModel: TodayForecastViewModel = viewModel(
        factory = TodayForecastViewModel.Factory(city)
    )

    val forecastList by viewModel.forecastList.collectAsState()
    val screenState by viewModel.screenState.collectAsState()

    ForecastContent(
        forecastList = forecastList,
        onErrorRetryClick = {viewModel.loadData()},
        screenState = screenState,
        isWindSpeed = true,
        topBarLabel = R.string.forecastToday
    )
}