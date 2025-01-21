package ua.sviatkuzbyt.cityweather.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ua.sviatkuzbyt.cityweather.ui.elements.basic.containerShape

/*Кольори:
* primary - голвна кнопка
* onPrimary - текст кнопки
* surface - фон
* onSurface - основний текст
* surfaceContainer - фон контейнерів
* onPrimaryContainer - дод. текст
* onSecondaryContainer - дод. іконики
*  */

private val DarkColorScheme = darkColorScheme(
    primary = Blue,
    onPrimary = Color.White,

    surface = Black,
    onSurface = Color.White,
    surfaceContainer = GrayDarkLow,

    onPrimaryContainer = Gray,
    onSecondaryContainer = GrayDark
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = Color.White,

    surface = Color.White,
    onSurface = Black,
    surfaceContainer = GrayLightLow,

    onPrimaryContainer = Gray,
    onSecondaryContainer = GrayLight
)

@Composable
fun CityWeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = MaterialTheme.shapes.copy(containerShape)
    )
}