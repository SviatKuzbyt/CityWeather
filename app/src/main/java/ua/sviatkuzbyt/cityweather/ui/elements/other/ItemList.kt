package ua.sviatkuzbyt.cityweather.ui.elements.other

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace56

@Composable
fun ItemList(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
){
    Row(
        modifier = modifier
            .padding(bottom = sizeSpace16)
            .fillMaxWidth()
            .heightIn(sizeSpace56)
            .containerBackground(),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}