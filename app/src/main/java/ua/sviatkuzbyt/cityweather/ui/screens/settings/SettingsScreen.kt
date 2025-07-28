package ua.sviatkuzbyt.cityweather.ui.screens.settings

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.LocalNavController
import ua.sviatkuzbyt.cityweather.ui.elements.other.ToastMessage
import ua.sviatkuzbyt.cityweather.ui.elements.screens.Screen
import ua.sviatkuzbyt.cityweather.ui.elements.topbar.LabelNavigateTopBar
import ua.sviatkuzbyt.cityweather.ui.screens.settings.elements.AboutItem
import ua.sviatkuzbyt.cityweather.ui.screens.settings.elements.SettingsItem
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16

@Composable
fun SettingsScreen(){
    // data
    val viewModel: SettingsViewModel = viewModel()
    val navController = LocalNavController.current

    val settingsList by viewModel.settingsList.collectAsState()
    val message by viewModel.message.collectAsStateWithLifecycle(
        minActiveState = Lifecycle.State.RESUMED
    )

    // ui
    Screen(
        topBar = {
            LabelNavigateTopBar(
                text = stringResource(R.string.settings),
                onNavigate = navController::navigateUp
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = sizeSpace16)
            ) {
                items(settingsList){
                    SettingsItem(
                        data = it,
                        onChangeValue = { setValue -> viewModel.setSettings(it.id, setValue) }
                    )
                }

                item { AboutItem() }
            }
        }
    )

    ToastMessage(message, viewModel::clearMessage)
}