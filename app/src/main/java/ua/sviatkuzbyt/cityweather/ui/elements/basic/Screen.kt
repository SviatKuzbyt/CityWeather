package ua.sviatkuzbyt.cityweather.ui.elements.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

//Контейнер для сторінок

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit,
    content: LazyListScope.() -> Unit
){
    Column(modifier.fillMaxSize()) {
        topBar()
        LazyColumn(Modifier.fillMaxSize().padding(horizontal = spaceMedium)) {
            content()
        }
    }
}