package ua.sviatkuzbyt.cityweather.ui.elements.cities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.basic.ButtonTopBar
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium
import ua.sviatkuzbyt.cityweather.ui.theme.spaceSmall

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun CitiesTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spaceMedium)
            .padding(bottom = spaceSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.weather),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row {
            ButtonTopBar(
                imageId = R.drawable.button_add,
                contentDescription = R.string.add_city
            ){}

            ButtonTopBar(
                imageId = R.drawable.button_more,
                contentDescription = R.string.open_settings
            ){}
        }
    }
}