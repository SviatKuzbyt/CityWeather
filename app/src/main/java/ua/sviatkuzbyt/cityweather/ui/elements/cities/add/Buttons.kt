package ua.sviatkuzbyt.cityweather.ui.elements.cities.add

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.basic.BasicText
import ua.sviatkuzbyt.cityweather.ui.elements.basic.HeadText
import ua.sviatkuzbyt.cityweather.ui.elements.basic.containerShape
import ua.sviatkuzbyt.cityweather.ui.theme.WhiteTransparent
import ua.sviatkuzbyt.cityweather.ui.theme.buttonHeight
import ua.sviatkuzbyt.cityweather.ui.theme.spaceSmall

@Composable
fun RowScope.AddButton(
    onClick: () -> Unit = {}
){
    Button(
        onClick = onClick,
        shape = containerShape,
        modifier = Modifier
            .height(buttonHeight)
            .weight(1f)
            .padding(start = spaceSmall),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = WhiteTransparent
        )
    ) {
        HeadText(
            text = stringResource(R.string.add),
            color = Color.White
        )
    }
}

@Composable
fun RowScope.CancelButton(
    onClick: () -> Unit = {}
){
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(buttonHeight)
            .weight(1f)
            .padding(end = spaceSmall),
        shape = containerShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        BasicText(stringResource(R.string.cancel))
    }
}