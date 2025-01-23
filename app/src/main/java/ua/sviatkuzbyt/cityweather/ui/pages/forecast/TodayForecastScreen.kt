package ua.sviatkuzbyt.cityweather.ui.pages.forecast

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.ForecastData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.ScreenState

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
        onErrorRetryClick = {},
        screenState = ScreenState.Content,
        isWindSpeed = true,
        topBarLabel = R.string.forecastToday
    )
}