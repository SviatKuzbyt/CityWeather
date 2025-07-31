package ua.sviatkuzbyt.cityweather.ui.elements.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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