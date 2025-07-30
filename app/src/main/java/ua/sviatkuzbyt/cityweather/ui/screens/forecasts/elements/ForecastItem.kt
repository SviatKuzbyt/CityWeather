package ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ua.sviatkuzbyt.cityweather.data.structures.weather.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.ui.elements.other.ItemList
import ua.sviatkuzbyt.cityweather.ui.elements.other.TextBasicCenter
import ua.sviatkuzbyt.cityweather.ui.elements.other.TextHeadCenter
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace24

@Composable
fun ForecastItem(data: ForecastData, weatherWeight: Float){
    ItemList {
        TextHeadCenter(data.time, modifier = Modifier.weight(1f))
        TextBasicCenter(data.temp, modifier = Modifier.weight(1f))

        data.windSpeed?.let {
            TextBasicCenter(it, modifier = Modifier.weight(1f))
        }

        Image(
            imageVector = ImageVector.vectorResource(data.weatherIcon),
            contentDescription = stringResource(data.weatherDescription),
            modifier = Modifier.height(sizeSpace24).weight(weatherWeight)
        )
    }
}