package ua.sviatkuzbyt.cityweather.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import ua.sviatkuzbyt.cityweather.ui.pages.cities.CitiesScreen
import ua.sviatkuzbyt.cityweather.ui.pages.forecast.FiveDaysForecastScreen
import ua.sviatkuzbyt.cityweather.ui.pages.forecast.today.TodayForecastScreen

@Serializable
data object CitiesRoute

@Serializable
data class ForecastTodayRoute(
    val city: String
)

@Serializable
data class ForecastFiveDaysRoute(
    val city: String
)

val LocalNavController: ProvidableCompositionLocal<NavController> = staticCompositionLocalOf{
    error("LocalNavController: No NavController")
}

val enterTransition =
    scaleIn(
        initialScale = 0.75f,
        animationSpec = tween(400, 100)
    ) + fadeIn(animationSpec = tween(400, 100))


val exitTransition =
    scaleOut(
        targetScale = 0.75f,
        animationSpec = tween(400)
    ) + fadeOut(animationSpec = tween(400))


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
            startDestination = CitiesRoute,
            enterTransition = { enterTransition },
            exitTransition = { exitTransition }
        ){
            composable<CitiesRoute> {
                CitiesScreen()
            }

            composable<ForecastTodayRoute>{
                val route: ForecastTodayRoute = it.toRoute()
                TodayForecastScreen(route.city)
            }

            composable<ForecastFiveDaysRoute>{
                FiveDaysForecastScreen()
            }
        }
    }
}