package ua.sviatkuzbyt.cityweather.ui.elements.cities.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.CityBackground
import ua.sviatkuzbyt.cityweather.data.structures.CityItemColors
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import ua.sviatkuzbyt.cityweather.data.structures.CityItemDetailData
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.DetailButton
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.DetailItem
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium
import ua.sviatkuzbyt.cityweather.ui.theme.spaceSmall

private val testData = CityItemData(1, "Test1", "+1°C", "1 m/s", R.drawable.weather_01d, R.string.weather_clear, CityBackground.BLue, 1, "1 mm", "+1°C", 1)

@Preview
@Composable
fun DetailCityItem(data: CityItemData = testData, colors: CityItemColors = CityItemColors.lightColors){

    val infoList = listOf(
        CityItemDetailData(R.drawable.ic_humidity, R.string.humidity, "${data.humidity}%"),
        CityItemDetailData(R.drawable.ic_pressure, R.string.pressure, data.pressure),
        CityItemDetailData(R.drawable.ic_feels_like, R.string.feels_like, data.feelsLike),
        CityItemDetailData(R.drawable.ic_rain, R.string.precipitation_reliability, "${data.rain}%")
    )

    Column(Modifier.fillMaxWidth()) {
        HorizontalDivider(
            color = colors.backColor,
            modifier = Modifier.padding(top = spaceMedium)
        )

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
