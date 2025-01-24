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
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.basic.topbar.ButtonTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextHeadLarge
import ua.sviatkuzbyt.cityweather.ui.elements.cities.add.AddCitySheet
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace8

@Composable
fun CitiesTopBar(
    onMoreButtonClick: () -> Unit,
    onAddCity: (String) -> Unit
) {
    val barModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = sizeSpace16)
        .padding(bottom = sizeSpace8)

    var showAddCitySheet by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = barModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        TextHeadLarge(stringResource(R.string.weather))

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