package ua.sviatkuzbyt.cityweather.ui.elements.basic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//Контейнер для сторінок

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit,
    content: @Composable () -> Unit
){
    Column(modifier.fillMaxSize()) {
        topBar()
        Box(Modifier.fillMaxSize()) {
            content()
        }
    }
}