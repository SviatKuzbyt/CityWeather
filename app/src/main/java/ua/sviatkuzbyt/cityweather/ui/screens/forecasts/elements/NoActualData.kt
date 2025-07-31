package ua.sviatkuzbyt.cityweather.ui.screens.forecasts.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.other.TextBasicCenter
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16

@Composable
fun NoActualData(){
    TextBasicCenter(
        text = stringResource(R.string.old_data),
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier.fillMaxWidth().padding(sizeSpace16)
    )
}