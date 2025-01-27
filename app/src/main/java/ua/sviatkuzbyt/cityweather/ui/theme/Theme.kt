package ua.sviatkuzbyt.cityweather.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.shapeContainer
/**
 * Кольори:
    * * primary - голвна кнопка (синій)
    * * onPrimary - текст кнопки (білий)
    * * surface - фон (білий/темний)
    * * onSurface - основний текст (чорний/білий)
    * * surfaceContainer - фон контейнерів (майже білий/чорний)
    * * onPrimaryContainer - дод. текст (сірий)
    * * onSecondaryContainer - дод. іконики (світло/темно сірий)
    */
private val DarkColorScheme = darkColorScheme(
    primary = Blue,
    onPrimary = Color.White,

    surface = Black,
    onSurface = Color.White,
    surfaceContainer = GrayDarkLow,

    onPrimaryContainer = Gray,
    onSecondaryContainer = GrayDark,
    onSurfaceVariant = Color.White,
    surfaceContainerHigh = Black
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = Color.White,

    surface = Color.White,
    onSurface = Black,
    surfaceContainer = GrayLightLow,

    onPrimaryContainer = Gray,
    onSecondaryContainer = GrayLight,
    onSurfaceVariant = Black,
    surfaceContainerHigh = Color.White
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
        shapes = MaterialTheme.shapes.copy(shapeContainer)
    )
}