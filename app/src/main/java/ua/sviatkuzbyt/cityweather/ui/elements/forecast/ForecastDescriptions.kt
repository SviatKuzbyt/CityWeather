package ua.sviatkuzbyt.cityweather.ui.elements.forecast

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.theme.iconSmallSize
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

@Composable
fun ForecastDescriptions(showWindSpeed: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spaceMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DescriptionIcon(
            icon = R.drawable.ic_forecast_time,
            contentDescription = R.string.time,
            modifier = Modifier.weight(1f)
        )

        DescriptionIcon(
            icon = R.drawable.ic_forecast_temperature,
            contentDescription = R.string.temperature,
            modifier = Modifier.weight(1f)
        )

        if(showWindSpeed){
            DescriptionIcon(
                icon = R.drawable.ic_forecast_wind,
                contentDescription = R.string.wind_speed,
                modifier = Modifier.weight(1f)
            )
        }

        DescriptionIcon(
            icon = R.drawable.ic_forecast_weather,
            contentDescription = R.string.weather,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DescriptionIcon(
    @DrawableRes icon: Int,
    @StringRes contentDescription: Int,
    modifier: Modifier = Modifier
){
    Icon(
        imageVector = ImageVector.vectorResource(icon),
        contentDescription = stringResource(contentDescription),
        tint = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = modifier.size(iconSmallSize)
    )
}