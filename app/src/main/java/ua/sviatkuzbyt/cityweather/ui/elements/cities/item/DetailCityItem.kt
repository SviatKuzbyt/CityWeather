package ua.sviatkuzbyt.cityweather.ui.elements.cities.item

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.CityItemColors
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import ua.sviatkuzbyt.cityweather.data.structures.CityItemDetailData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.BasicText
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.ControlMenu
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.DetailButton
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.DetailItem
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium
import ua.sviatkuzbyt.cityweather.ui.theme.spaceOverSmall
import ua.sviatkuzbyt.cityweather.ui.theme.spaceSmall

@Composable
fun DetailCityItem(data: CityItemData, colors: CityItemColors){

    //List of weather details
    val infoList = listOf(
        CityItemDetailData(R.drawable.ic_humidity, R.string.humidity, "${data.humidity}%"),
        CityItemDetailData(R.drawable.ic_pressure, R.string.pressure, data.pressure),
        CityItemDetailData(R.drawable.ic_feels_like, R.string.feels_like, data.feelsLike),
        CityItemDetailData(R.drawable.ic_rain, R.string.precipitation_reliability, "${data.rain}%")
    )

    var showControlMenu by remember {
        mutableStateOf(false)
    }

    //Set UI
    Column(Modifier.fillMaxWidth()) {
        HorizontalDivider(
            color = colors.backColor,
            modifier = Modifier.padding(top = spaceMedium)
        )

        //Weather details
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = spaceMedium)
        ) {
            infoList.forEach {
                DetailItem(
                    icon =  it.icon,
                    contentDescription = it.contentDescription,
                    colors = colors,
                    textContent = it.textContent
                )
            }
        }

        //Buttons
        DetailButton(
            label = R.string.forecastToday,
            colors = colors
        ) { }

        DetailButton(
            label = R.string.forecastFiveDays,
            colors = colors
        ) { }

        Box {
            DetailButton(
                label = R.string.control,
                colors = colors
            ) {
                showControlMenu = true
            }

            ControlMenu(
                show = showControlMenu,
                onHide = {showControlMenu = false},
                onDelete = {},
                onMoveUp = {}
            )
        }

    }
}

