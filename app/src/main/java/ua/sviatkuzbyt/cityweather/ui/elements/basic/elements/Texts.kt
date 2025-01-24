package ua.sviatkuzbyt.cityweather.ui.elements.basic.elements

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import ua.sviatkuzbyt.cityweather.ui.theme.sizeText24
import ua.sviatkuzbyt.cityweather.ui.theme.sizeText16
import ua.sviatkuzbyt.cityweather.ui.theme.sizeText14

@Composable
fun TextBasic(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontSize = sizeText16,
        style = MaterialTheme.typography.displayMedium,
        color = color,
        modifier = modifier
    )
}

@Composable
fun TextBasicLarge(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontSize = sizeText24,
        style = MaterialTheme.typography.displayMedium,
        color = color,
        modifier = modifier
    )
}

@Composable
fun TextBasicSmall(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontSize = sizeText14,
        style = MaterialTheme.typography.displayMedium,
        color = color,
        modifier = modifier
    )
}

@Composable
fun TextHead(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontSize = sizeText16,
        style = MaterialTheme.typography.headlineMedium,
        color = color,
        modifier = modifier
    )
}

@Composable
fun TextHeadLarge(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontSize = sizeText24,
        style = MaterialTheme.typography.headlineMedium,
        color = color,
        modifier = modifier
    )
}

@Composable
fun TextBasicCenter(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontSize = sizeText16,
        style = MaterialTheme.typography.displayMedium,
        color = color,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
fun TextHeadCenter(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontSize = sizeText16,
        style = MaterialTheme.typography.headlineMedium,
        color = color,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}