package ua.sviatkuzbyt.cityweather.ui.elements.cities.item.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ua.sviatkuzbyt.cityweather.data.structures.CityItemColors
import ua.sviatkuzbyt.cityweather.ui.theme.buttonHeight
import ua.sviatkuzbyt.cityweather.ui.theme.spaceLarge
import ua.sviatkuzbyt.cityweather.ui.theme.spaceSmall

@Composable
fun DetailButton(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    colors: CityItemColors,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(spaceLarge),
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.backColor,
            contentColor = colors.iconColor
        ),
        modifier = modifier
            .padding(top = spaceSmall)
            .fillMaxWidth()
            .height(buttonHeight)
    ) {
        Text(
            text = stringResource(label),
            style = MaterialTheme.typography.displayMedium,
            color = colors.textColor
        )
    }
}