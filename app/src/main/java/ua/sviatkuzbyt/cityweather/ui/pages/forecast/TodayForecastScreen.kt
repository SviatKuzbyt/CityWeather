package ua.sviatkuzbyt.cityweather.ui.pages.forecast

import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.ForecastData
import ua.sviatkuzbyt.cityweather.ui.LocalNavController
import ua.sviatkuzbyt.cityweather.ui.elements.forecast.ForecastItem
import ua.sviatkuzbyt.cityweather.ui.elements.basic.LabelNavigateTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.basic.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.basic.ScreenState
import ua.sviatkuzbyt.cityweather.ui.elements.forecast.ForecastDescriptions

@Preview
@Composable
fun TodayForecastScreen(){
    val tempList = listOf(
        ForecastData(
            "15:00",
            "+20C",
            "5 m/s",
            R.drawable.weather_02d,
            "sun"
        ),
        ForecastData(
            "18:00",
            "+2C",
            "3 m/s",
            R.drawable.weather_02d,
            "sun"
        ),
        ForecastData(
            "21:00",
            "+17C",
            "21 m/s",
            R.drawable.weather_02d,
            "sun"
        )
    )
    ForecastContent(
        forecastList = tempList,
        onErrorRetryClick = {}
    )
}

@Composable
fun ForecastContent(
    forecastList: List<ForecastData>,
    onErrorRetryClick: () -> Unit
){
    val navController = LocalNavController.current

    val isWindSpeed = forecastList[0].windSpeed != null

    Screen(
        topBar = {
            LabelNavigateTopBar(
                text = stringResource(R.string.forecastToday),
                onNavigate = { navController.navigateUp() }
            )
        },
        screenState = ScreenState.Content,
        onErrorRetryClick = onErrorRetryClick,
        content = {
            item {
                ForecastDescriptions(isWindSpeed)
            }

            items(forecastList){
                ForecastItem(it)
            }
        }
    )
}
