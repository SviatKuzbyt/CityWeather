package ua.sviatkuzbyt.cityweather.ui.elements.forecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.cityweather.data.structures.ForecastData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.BasicText
import ua.sviatkuzbyt.cityweather.ui.elements.basic.HeadText
import ua.sviatkuzbyt.cityweather.ui.elements.basic.containerShape
import ua.sviatkuzbyt.cityweather.ui.theme.iconMediumSize
import ua.sviatkuzbyt.cityweather.ui.theme.spaceLarge
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

@Composable
fun ForecastItem(
    data: ForecastData
){
    Row(
        modifier = Modifier
            .padding(bottom = spaceMedium)
            .fillMaxWidth()
            .heightIn(56.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = containerShape
            )
            .padding(horizontal = spaceLarge),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeadText(data.time)
        BasicText(data.temp)

        data.windSpeed?.let {
            BasicText(it)
        }

        Image(
            imageVector = ImageVector.vectorResource(data.weatherIcon),
            contentDescription = data.contentDescription,
            modifier = Modifier.size(iconMediumSize)
        )
    }
}