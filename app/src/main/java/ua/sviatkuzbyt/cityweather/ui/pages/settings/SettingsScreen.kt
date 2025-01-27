package ua.sviatkuzbyt.cityweather.ui.pages.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.ScreenState
import ua.sviatkuzbyt.cityweather.data.structures.settings.SettingsItemData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.screens.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.basic.topbar.LabelNavigateTopBar
import ua.sviatkuzbyt.cityweather.ui.elements.settings.SettingsItem

@Composable
fun SettingsScreen(){
    SettingsContent{}
}

@Composable
fun SettingsContent(
    navigateBack: () -> Unit
){
    Screen(
        topBar = {
            LabelNavigateTopBar(
                text = stringResource(R.string.settings),
                onNavigate = navigateBack
            )
        },
        screenState = ScreenState.Content
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(10){
                SettingsItem(
                    data = SettingsItemData(
                        R.string.units,
                        "standard"
                    ) { listOf("standard", "metric", "imperial") },
                    onChangeValue = {}
                )
            }
        }
    }
}