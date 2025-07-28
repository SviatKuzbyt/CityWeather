package ua.sviatkuzbyt.cityweather.ui.elements.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.cityweather.ui.elements.other.TextBasicCenter
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace36

@Composable
fun LazyItemScope.TextPlugList(
    text: String,
    modifier: Modifier = Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillParentMaxSize().padding(horizontal = sizeSpace16)
    ){
        TextBasicCenter(
            text = text,
            modifier = modifier,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun TextPlug(
    text: String,
    modifier: Modifier = Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().padding(horizontal = sizeSpace36)
    ){
        TextBasicCenter(
            text = text,
            modifier = modifier,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun LoadPlug(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.width(sizeSpace36),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceContainer,
            strokeWidth = 2.dp
        )
    }
}