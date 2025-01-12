@file:OptIn(ExperimentalMaterial3Api::class)

package ua.sviatkuzbyt.cityweather.ui.elements.cities.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.basic.HeadText
import ua.sviatkuzbyt.cityweather.ui.elements.basic.SpaceMedium
import ua.sviatkuzbyt.cityweather.ui.elements.basic.SpaceSmall
import ua.sviatkuzbyt.cityweather.ui.theme.spaceLarge
import ua.sviatkuzbyt.cityweather.ui.theme.spaceMedium

@Preview
@Composable
fun AddCitySheet(
    isShow: Boolean = false,
    hideAction: () -> Unit = {},
    addAction: (String) -> Unit = {}
){

    val showState = rememberModalBottomSheetState()
    val screenScope = rememberCoroutineScope()

    if (isShow){
        ModalBottomSheet(
            onDismissRequest = hideAction,
            sheetState = showState,
            shape = RoundedCornerShape(topEnd = spaceLarge, topStart = spaceLarge),
            containerColor = MaterialTheme.colorScheme.surface,
            tonalElevation = 0.dp,

        ) {
            var cityName by rememberSaveable {
                mutableStateOf("")
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = spaceMedium)
                    .padding(bottom = spaceMedium)
            ){
                HeadText(stringResource(R.string.add_city))

                SpaceSmall()

                CityTextField(
                    value = cityName,
                    onValueChange = { newValue ->
                        cityName = newValue
                    },
                    keyboardAction = {
                        addAction(cityName)
                        hideScreen(screenScope, showState, hideAction)
                    }
                )

                SpaceMedium()

                Row(modifier = Modifier.fillMaxWidth()) {
                    CancelButton {
                        hideScreen(screenScope, showState, hideAction)
                    }

                    AddButton {
                        addAction(cityName)
                        hideScreen(screenScope, showState, hideAction)
                    }
                }
            }
        }
    }
}

private fun hideScreen(
    screenScope: CoroutineScope,
    showState: SheetState,
    hideAction: () -> Unit
){
    screenScope.launch {
        showState.hide()
    }.invokeOnCompletion {
        if (!showState.isVisible){
            hideAction()
        }
    }
}
