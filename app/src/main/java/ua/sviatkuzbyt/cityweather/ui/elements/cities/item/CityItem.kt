package ua.sviatkuzbyt.cityweather.ui.elements.cities.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.data.structures.CityItemData
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.getCityBackground
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.getCityItemColors
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.getLinearBackground
import ua.sviatkuzbyt.cityweather.ui.theme.spaceLarge
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

@Composable
fun CityItem(
    data: CityItemData,
    isOpen: Boolean,
    onClickItem: () -> Unit
){
    // Set colors for content
    val backgrounds = getCityBackground(data.background)
    val colors = getCityItemColors(backgrounds[0])

    //Set width, padding, background and animation
    val modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = spaceMedium)
        .background(
            brush = getLinearBackground(backgrounds),
            shape = RoundedCornerShape(spaceMedium)
        )
        .clickable(
            interactionSource = null,
            indication = null,) {
            onClickItem()
        }
        .padding(vertical = spaceMedium, horizontal = spaceLarge)
        .animateContentSize(
            animationSpec = tween(400, 0)
        )

    //Set UI
    Column(modifier) {
        MainCityItem(data, colors)

        AnimatedVisibility(
            visible = isOpen,
            enter = fadeIn(animationSpec = tween(100)),
            exit = fadeOut(animationSpec = tween(100))
        ) {
            DetailCityItem(data, colors)
        }
    }
}

