package ua.sviatkuzbyt.cityweather.ui.elements.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium
import ua.sviatkuzbyt.cityweather.ui.theme.spaceSmall

@Composable
fun LabelNavigateTopBar(
    text: String,
    onNavigate: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = spaceSmall)
    ) {
        ButtonTopBar(
            imageId = R.drawable.ic_back,
            contentDescription = R.string.close_page,
            onClick = onNavigate,
            modifier = Modifier.padding(start = spaceSmall)
        )

        HeadLargeText(
            text = text,
            modifier = Modifier.padding(horizontal = spaceMedium).padding(bottom = spaceSmall)
        )
    }
}