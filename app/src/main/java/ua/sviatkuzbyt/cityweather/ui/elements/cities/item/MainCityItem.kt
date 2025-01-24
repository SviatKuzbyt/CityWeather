package ua.sviatkuzbyt.cityweather.ui.elements.cities.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemColors
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextBasicLarge
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextBasicSmall
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextHead
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace36
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace20
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16

@Composable
fun MainCityItem(data: CityItemData, colors: CityItemColors){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        //City name text
        TextHead(
            text = data.name,
            color = colors.textColor,
            modifier = Modifier
                .weight(1f)
                .padding(end = sizeSpace16)
        )

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(end = sizeSpace20)
        ) {
            //Temperature text
            TextBasicLarge(
                text = data.temperature,
                color = colors.textColor
            )

            //Wind speed text
            TextBasicSmall(
                text = "${stringResource(R.string.wind_speed)} ${data.windSpeed}",
                color = colors.iconColor
            )
        }

        //Weather image
        Image(
            imageVector = ImageVector.vectorResource(data.image),
            contentDescription = data.weatherDescription,
            modifier = Modifier.size(sizeSpace36)
        )
    }
}