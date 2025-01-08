package ua.sviatkuzbyt.cityweather.ui.pages.cities

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ua.sviatkuzbyt.cityweather.ui.elements.basic.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.cities.CitiesTopBar

@Preview
@Composable
fun CitiesScreen(){
    Screen(
        topBar = { CitiesTopBar() }
    ) {}
}