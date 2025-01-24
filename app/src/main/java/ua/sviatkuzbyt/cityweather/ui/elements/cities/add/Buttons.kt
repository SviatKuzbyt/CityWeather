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
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextBasic
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextHead
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.shapeContainer
import ua.sviatkuzbyt.cityweather.ui.theme.WhiteTransparent
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace48
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace8

@Composable
fun RowScope.AddButton(
    onClick: () -> Unit = {}
){
    Button(
        onClick = onClick,
        shape = shapeContainer,
        modifier = Modifier
            .height(sizeSpace48)
            .weight(1f)
            .padding(start = sizeSpace8),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = WhiteTransparent
        )
    ) {
        TextHead(
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
            .height(sizeSpace48)
            .weight(1f)
            .padding(end = sizeSpace8),
        shape = shapeContainer,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        TextBasic(stringResource(R.string.cancel))
    }
}