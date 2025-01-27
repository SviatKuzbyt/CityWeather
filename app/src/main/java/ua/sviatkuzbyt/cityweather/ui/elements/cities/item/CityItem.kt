package ua.sviatkuzbyt.cityweather.ui.elements.cities.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.basicClick
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.getCityBackground
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.getCityItemColors
import ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components.getLinearBackground
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace20
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16

@Composable
fun CityItem(
    data: CityItemData,
    isOpen: Boolean,
    onClickItem: () -> Unit,
    onDelete: () -> Unit,
    onMoveUp: () -> Unit,
    onTodayClick: (String) -> Unit,
    onFiveDaysClick: (String) -> Unit
){
    // Set colors for content
    val backgrounds = getCityBackground(data.background)
    val colors = getCityItemColors(backgrounds[0])

    //Set width, padding, background and animation
    val modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = sizeSpace16)
        .background(
            brush = getLinearBackground(backgrounds),
            shape = RoundedCornerShape(sizeSpace16)
        )
        .basicClick {
            onClickItem()
        }
        .padding(vertical = sizeSpace16, horizontal = sizeSpace20)
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
            DetailCityItem(data, colors, onDelete, onMoveUp, onTodayClick, onFiveDaysClick)
        }
    }
}

