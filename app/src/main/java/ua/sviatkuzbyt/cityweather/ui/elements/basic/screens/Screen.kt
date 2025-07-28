package ua.sviatkuzbyt.cityweather.ui.elements.basic.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit,
    content: @Composable () -> Unit
){
    Column(modifier.fillMaxSize()) {
        topBar()
        content()
    }
}