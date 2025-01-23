package ua.sviatkuzbyt.cityweather.ui.pages.forecast

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.LocalNavController
import ua.sviatkuzbyt.cityweather.ui.elements.basic.LabelNavigateTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.basic.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.basic.ScreenState

@Composable
fun TodayForecastScreen(){
    ForecastContent()
}

@Composable
fun ForecastContent(
){
    val navController = LocalNavController.current
    Screen(
        topBar = {
            LabelNavigateTopBar(
                text = stringResource(R.string.forecastToday),
                onNavigate = { navController.navigateUp() }
            )
        },
        screenState = ScreenState.Content,
        onErrorRetryClick = {},
        content = {}
    )
}
