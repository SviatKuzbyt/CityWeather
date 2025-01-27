package ua.sviatkuzbyt.cityweather.ui.elements.basic.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace56

@Composable
fun ItemList(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical,
    content: @Composable RowScope.() -> Unit
){
    Row(
        modifier = modifier
            .padding(bottom = sizeSpace16)
            .fillMaxWidth()
            .heightIn(sizeSpace56)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = shapeContainer
            ),
        verticalAlignment = verticalAlignment,
        horizontalArrangement = Arrangement.Center,
        content = content
    )
}