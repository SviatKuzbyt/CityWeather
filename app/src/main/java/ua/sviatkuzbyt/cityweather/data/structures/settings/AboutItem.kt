package ua.sviatkuzbyt.cityweather.data.structures.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextBasic
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextHead
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.containerBackground
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace20
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace8

@Composable
fun AboutItem(){
    Column(
        modifier = Modifier
            .containerBackground()
            .padding(vertical = sizeSpace16, horizontal = sizeSpace20)
    ) {
        TextHead(
            text = stringResource(R.string.about_tittle),
            modifier = Modifier.padding(bottom = sizeSpace8)
        )

        TextBasic(stringResource(R.string.about_content))
    }
}