package ua.sviatkuzbyt.cityweather.ui.elements.cities.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemColors
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemData
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemDetailData
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.ControlMenu
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.DetailButton
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.DetailItem
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16

@Composable
fun DetailCityItem(
    data: CityItemData,
    colors: CityItemColors,
    onDelete: () -> Unit,
    onMoveUp: () -> Unit,
    onTodayClick: (String) -> Unit,
    onFiveDaysClick: (String) -> Unit
){
    //List of weather details
    val infoList = listOf(
        CityItemDetailData(R.drawable.ic_humidity, R.string.humidity, "${data.humidity}%"),
        CityItemDetailData(R.drawable.ic_pressure, R.string.pressure, "${data.pressure} hPa"),
        CityItemDetailData(R.drawable.ic_feels_like, R.string.feels_like, data.feelsLike),
        CityItemDetailData(R.drawable.ic_rain, R.string.precipitation_reliability, "${data.rain} mm/h")
    )

    var showControlMenu by remember {
        mutableStateOf(false)
    }

    //Set UI
    Column(Modifier.fillMaxWidth()) {
        HorizontalDivider(
            color = colors.backColor,
            modifier = Modifier.padding(top = sizeSpace16)
        )

        //Weather details
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = sizeSpace16)
        ) {
            infoList.forEach {
                DetailItem(content = it, colors = colors)
            }
        }

        //Buttons
        DetailButton(label = R.string.forecastToday, colors = colors) {
            onTodayClick(data.name)
        }

        DetailButton(label = R.string.forecastFiveDays, colors = colors) {
            onFiveDaysClick(data.name)
        }

        Box {
            DetailButton(label = R.string.control, colors = colors) {
                showControlMenu = true
            }

            ControlMenu(
                show = showControlMenu,
                onHide = {showControlMenu = false},
                onDelete = onDelete,
                onMoveUp = onMoveUp
            )
        }
    }
}