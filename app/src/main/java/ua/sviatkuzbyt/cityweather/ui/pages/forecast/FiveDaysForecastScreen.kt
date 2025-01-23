package ua.sviatkuzbyt.cityweather.ui.pages.forecast

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.api.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.ScreenState

@Preview
@Composable
fun FiveDaysForecastScreen(){
    val tempList = listOf(
        ForecastData(
            "Пн, 20.05",
            "+20℃ / +12℃",
            null,
            R.drawable.weather_02d,
            "sun"
        ),
        ForecastData(
            "Вт, 21.05",
            "+23℃ / +14℃",
            null,
            R.drawable.weather_01d,
            "sun"
        ),
        ForecastData(
            "Ср, 22.05",
            "+26℃ / +16℃",
            null,
            R.drawable.weather_01d,
            "sun"
        ),
        ForecastData(
            "Чт, 23.05",
            "+18℃ / +9℃",
            null,
            R.drawable.weather_09,
            "rain"
        )
    )

    ForecastContent(
        forecastList = tempList,
        onErrorRetryClick = {},
        screenState = ScreenState.Content,
        isWindSpeed = false,
        topBarLabel = R.string.forecastFiveDays
    )
}
