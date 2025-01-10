package ua.sviatkuzbyt.cityweather.ui.elements.basic

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ua.sviatkuzbyt.cityweather.ui.theme.textLargeSize
import ua.sviatkuzbyt.cityweather.ui.theme.textMediumSize
import ua.sviatkuzbyt.cityweather.ui.theme.textSmallSize


@Composable
fun BasicText(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontSize = textMediumSize,
        style = MaterialTheme.typography.displayMedium,
        color = color,
        modifier = modifier
    )
}

@Composable
fun BasicLargeText(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontSize = textLargeSize,
        style = MaterialTheme.typography.displayMedium,
        color = color,
        modifier = modifier
    )
}

@Composable
fun BasicSmallText(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontSize = textSmallSize,
        style = MaterialTheme.typography.displayMedium,
        color = color,
        modifier = modifier
    )
}

@Composable
fun HeadText(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontSize = textMediumSize,
        style = MaterialTheme.typography.headlineMedium,
        color = color,
        modifier = modifier
    )
}

@Composable
fun HeadLargeText(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontSize = textLargeSize,
        style = MaterialTheme.typography.headlineMedium,
        color = color,
        modifier = modifier
    )
}