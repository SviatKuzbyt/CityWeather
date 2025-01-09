package ua.sviatkuzbyt.cityweather.ui.elements.cities.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.CityItemColors
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import ua.sviatkuzbyt.cityweather.data.structures.CityItemDetailData
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.DetailButton
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.DetailItem
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

@Composable
fun DetailCityItem(data: CityItemData, colors: CityItemColors){

    //List of weather details
    val infoList = listOf(
        CityItemDetailData(R.drawable.ic_humidity, R.string.humidity, "${data.humidity}%"),
        CityItemDetailData(R.drawable.ic_pressure, R.string.pressure, data.pressure),
        CityItemDetailData(R.drawable.ic_feels_like, R.string.feels_like, data.feelsLike),
        CityItemDetailData(R.drawable.ic_rain, R.string.precipitation_reliability, "${data.rain}%")
    )

    //Set UI
    Column(Modifier.fillMaxWidth()) {
        HorizontalDivider(
            color = colors.backColor,
            modifier = Modifier.padding(top = spaceMedium)
        )

        //Weather details
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            modifier = Modifier.fillMaxWidth().padding(vertical = spaceMedium)
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

        DetailButton(
            label = R.string.control,
            colors = colors
        ) { }
    }
}