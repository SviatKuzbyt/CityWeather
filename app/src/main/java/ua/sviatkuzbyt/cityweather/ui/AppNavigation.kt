package ua.sviatkuzbyt.cityweather.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import ua.sviatkuzbyt.cityweather.ui.pages.cities.CitiesScreen

@Serializable
data object CitiesRoute

val LocalNavController: ProvidableCompositionLocal<NavController> = staticCompositionLocalOf{
    error("LocalNavController: No NavController")
}

@Composable
fun AppNavigation(){
    val modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surface)
        .windowInsetsPadding(WindowInsets.systemBars)

    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController ) {
        //Розміщення маршрутів навігації
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = CitiesRoute
        ){
            composable<CitiesRoute> {
                CitiesScreen()
            }
        }
    }
}