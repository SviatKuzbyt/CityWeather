package ua.sviatkuzbyt.cityweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import ua.sviatkuzbyt.cityweather.ui.theme.CityWeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CityWeatherTheme {
                Text("Hello world!")
            }
        }
    }
}