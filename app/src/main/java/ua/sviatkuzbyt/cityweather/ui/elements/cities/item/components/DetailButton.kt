package ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemColors
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextBasic
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.shapeContainer
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace48
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace8

@Composable
fun DetailButton(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    colors: CityItemColors,
    onClick: () -> Unit
){
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = colors.backColor,
        contentColor = colors.iconColor
    )

    Button(
        onClick = onClick,
        shape = shapeContainer,
        colors = buttonColors,
        modifier = modifier
            .padding(top = sizeSpace8)
            .fillMaxWidth()
            .height(sizeSpace48)
    ) {
        TextBasic(
            text = stringResource(label),
            color = colors.textColor
        )
    }
}