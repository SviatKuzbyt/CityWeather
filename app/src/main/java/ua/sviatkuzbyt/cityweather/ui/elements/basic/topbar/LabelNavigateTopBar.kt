package ua.sviatkuzbyt.cityweather.ui.elements.basic.topbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextHeadLarge
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace8

@Composable
fun LabelNavigateTopBar(
    text: String,
    onNavigate: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = sizeSpace8)
    ) {
        ButtonTopBar(
            imageId = R.drawable.ic_back,
            contentDescription = R.string.close_page,
            onClick = onNavigate,
            modifier = Modifier.padding(start = sizeSpace8)
        )

        TextHeadLarge(
            text = text,
            modifier = Modifier
                .padding(horizontal = sizeSpace16)
                .padding(bottom = sizeSpace8)
        )
    }
}