package ua.sviatkuzbyt.cityweather.ui.elements.cities.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.CityBackground
import ua.sviatkuzbyt.cityweather.data.structures.CityData
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.getCityBackground
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.getContrastingColor
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.getContrastingTransparentColor
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.getLinearBackground
import ua.sviatkuzbyt.cityweather.ui.theme.spaceLarge
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

@Preview(showBackground = true, device = "spec:width=2080px,height=2340px,dpi=440")
@Composable
fun CityItem(
    data: CityData = CityData(1, "Sambir", "+5 C", "4 m/s", R.drawable.weather_02n, R.string.weather_clear, CityBackground.BLue, 0, "0", "0", 0)
){
    // Set colors for content
    val backgrounds = getCityBackground(data.background)
    val contentColor = getContrastingColor(backgrounds[0])
    val contentTransparentColor = getContrastingTransparentColor(backgrounds[0])

    //Set width, padding and background
    val modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = spaceMedium)
        .background(
            brush = getLinearBackground(backgrounds),
            shape = RoundedCornerShape(spaceMedium)
        )
        .padding(vertical = spaceMedium, horizontal = spaceLarge)

    Column(modifier) {
        MainCityItem(data, contentColor, contentTransparentColor)
    }
}

