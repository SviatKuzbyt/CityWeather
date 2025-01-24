package ua.sviatkuzbyt.cityweather.ui.elements.forecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.cityweather.data.structures.forecast.ForecastData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.CenterBasicText
import ua.sviatkuzbyt.cityweather.ui.elements.basic.CenterHeadText
import ua.sviatkuzbyt.cityweather.ui.elements.basic.containerShape
import ua.sviatkuzbyt.cityweather.ui.theme.iconMediumSize
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

@Composable
fun ForecastItem(data: ForecastData, weatherWeight: Float){
    Row(
        modifier = Modifier
            .padding(bottom = spaceMedium)
            .fillMaxWidth()
            .heightIn(56.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = containerShape
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CenterHeadText(data.time, modifier = Modifier.weight(1f))
        CenterBasicText(data.temp, modifier = Modifier.weight(1f))

        data.windSpeed?.let {
            CenterBasicText(it, modifier = Modifier.weight(1f))
        }

        Image(
            imageVector = ImageVector.vectorResource(data.weatherIcon),
            contentDescription = data.contentDescription,
            modifier = Modifier.height(iconMediumSize).weight(weatherWeight)
        )
    }
}