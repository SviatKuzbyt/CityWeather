package ua.sviatkuzbyt.cityweather.ui.elements.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

enum class ScreenState {
    Loading, Content, Empty, Error
}

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit,
    screenState: ScreenState = ScreenState.Loading,
    emptyText: String,
    content: LazyListScope.() -> Unit,

){
    Column(modifier.fillMaxSize()) {
        topBar()
        when(screenState){
            ScreenState.Loading -> {
                LoadScreen()
            }
            ScreenState.Content -> {
                LazyColumn(Modifier.fillMaxSize().padding(horizontal = spaceMedium)) {
                    content()
                }
            }
            ScreenState.Empty -> {
                EmptyTextScreen(emptyText)
            }
            ScreenState.Error -> {}
        }
    }
}