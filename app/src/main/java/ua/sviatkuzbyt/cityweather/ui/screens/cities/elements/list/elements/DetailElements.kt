package ua.sviatkuzbyt.cityweather.ui.screens.cities.elements.list.elements

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemColors
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemDetailData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.SpacerSmall
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextBasic
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextBasicSmall
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.shapeContainer
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace20
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace48
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace8

@Composable
fun DetailItem(
    modifier: Modifier = Modifier,
    content: CityItemDetailData,
    colors: CityItemColors,
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(content.icon),
            contentDescription = stringResource(content.contentDescription),
            tint = colors.iconColor,
            modifier = Modifier.size(sizeSpace20)
        )

        SpacerSmall()
        Spacer(Modifier.height(sizeSpace8))

        TextBasicSmall(
            text = content.textContent,
            color = colors.textColor
        )
    }
}

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