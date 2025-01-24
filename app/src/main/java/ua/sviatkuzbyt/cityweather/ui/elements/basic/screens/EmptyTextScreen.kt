package ua.sviatkuzbyt.cityweather.ui.elements.basic.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextBasicCenter
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16

@Composable
fun EmptyTextScreen(text: String){
    Box(
        modifier = Modifier.fillMaxSize().padding(sizeSpace16),
        contentAlignment = Alignment.Center
    ){
        TextBasicCenter(
            text = text,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}