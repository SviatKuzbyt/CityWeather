package ua.sviatkuzbyt.cityweather.ui.elements.cities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.basic.ButtonTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.basic.HeadLargeText
import ua.sviatkuzbyt.cityweather.ui.elements.cities.add.AddCitySheet
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium
import ua.sviatkuzbyt.cityweather.ui.theme.spaceSmall

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun CitiesTopBar(
    onMoreButtonClick: () -> Unit = {},
    onAddCity: (String) -> Unit = {}
) {
    val barModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = spaceMedium)
        .padding(bottom = spaceSmall)

    var showAddCitySheet by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = barModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        HeadLargeText(stringResource(R.string.weather))

        Row {
            ButtonTopBar(
                imageId = R.drawable.button_add,
                contentDescription = R.string.add_city,
                onClick = {showAddCitySheet = true}
            )

            AddCitySheet(
                showAddCitySheet,
                {showAddCitySheet = false},
                onAddCity
            )

            ButtonTopBar(
                imageId = R.drawable.button_more,
                contentDescription = R.string.open_settings,
                onClick = onMoreButtonClick
            )
        }
    }
}