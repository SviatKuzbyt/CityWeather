package ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ua.sviatkuzbyt.cityweather.data.structures.cities.CityItemColors
import ua.sviatkuzbyt.cityweather.ui.elements.basic.BasicSmallText
import ua.sviatkuzbyt.cityweather.ui.elements.basic.SpaceSmall
import ua.sviatkuzbyt.cityweather.ui.theme.iconMediumSmallSize
import ua.sviatkuzbyt.cityweather.ui.theme.spaceSmall

@Composable
fun DetailItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes contentDescription: Int,
    colors: CityItemColors,
    textContent: String
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = stringResource(contentDescription),
            tint = colors.iconColor,
            modifier = Modifier.size(iconMediumSmallSize)
        )

        SpaceSmall()
        Spacer(Modifier.height(spaceSmall))

        BasicSmallText(
            text = textContent,
            color = colors.textColor
        )
    }
}