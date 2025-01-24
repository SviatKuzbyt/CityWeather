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
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextBasicCenter
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextHeadCenter
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.shapeContainer
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace24
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16

@Composable
fun ForecastItem(data: ForecastData, weatherWeight: Float){
    Row(
        modifier = Modifier
            .padding(bottom = sizeSpace16)
            .fillMaxWidth()
            .heightIn(56.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = shapeContainer
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextHeadCenter(data.time, modifier = Modifier.weight(1f))
        TextBasicCenter(data.temp, modifier = Modifier.weight(1f))

        data.windSpeed?.let {
            TextBasicCenter(it, modifier = Modifier.weight(1f))
        }

        Image(
            imageVector = ImageVector.vectorResource(data.weatherIcon),
            contentDescription = data.contentDescription,
            modifier = Modifier.height(sizeSpace24).weight(weatherWeight)
        )
    }
}