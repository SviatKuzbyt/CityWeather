package ua.sviatkuzbyt.cityweather.ui.pages.forecast.fivedays

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.repositories.ForecastFiveDaysRepository
import ua.sviatkuzbyt.cityweather.ui.pages.forecast.ForecastContent
import ua.sviatkuzbyt.cityweather.ui.pages.forecast.ForecastViewModel

@Composable
fun FiveDaysForecastScreen(city: String){

    val viewModel: ForecastViewModel = viewModel(
        factory = ForecastViewModel.Factory(ForecastFiveDaysRepository(city))
    )

    val forecastList by viewModel.forecastList.collectAsState()
    val screenState by viewModel.screenState.collectAsState()

    ForecastContent(
        forecastList = forecastList,
        onErrorRetryClick = {viewModel.loadData()},
        screenState = screenState,
        isWindSpeed = false,
        topBarLabel = R.string.forecastFiveDays
    )
}