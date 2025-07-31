package ua.sviatkuzbyt.cityweather.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ua.sviatkuzbyt.cityweather.ui.AppNavigation
import ua.sviatkuzbyt.cityweather.ui.theme.CityWeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CityWeatherTheme {
                AppNavigation()
            }
        }
    }
}