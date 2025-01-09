package ua.sviatkuzbyt.cityweather.ui.elements.cities.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.CityItemColors
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import ua.sviatkuzbyt.cityweather.ui.theme.iconLargeSize
import ua.sviatkuzbyt.cityweather.ui.theme.spaceLarge
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

@Composable
fun MainCityItem(data: CityItemData, colors: CityItemColors){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        //City name text
        Text(
            text = data.name,
            style = MaterialTheme.typography.headlineMedium,
            color = colors.textColor,
            modifier = Modifier
                .weight(1f)
                .padding(end = spaceMedium)
        )

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(end = spaceLarge)
        ) {
            //Temperature text
            Text(
                text = data.temperature,
                style = MaterialTheme.typography.displayLarge,
                color = colors.textColor
            )

            //Wind speed text
            Text(
                text = "${stringResource(R.string.wind_speed)} ${data.windSpeed}",
                style = MaterialTheme.typography.displaySmall,
                color = colors.iconColor
            )
        }

        //Weather image
        Image(
            imageVector = ImageVector.vectorResource(data.image),
            contentDescription = stringResource(data.weatherDescription),
            modifier = Modifier.size(iconLargeSize)
        )
    }
}