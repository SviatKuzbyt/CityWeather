@file:OptIn(ExperimentalMaterial3Api::class)
package ua.sviatkuzbyt.cityweather.ui.elements.cities.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextHead
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.SpacerMedium
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.SpacerSmall
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace20

@Composable
fun AddCityDialog(
    hideAction: () -> Unit,
    addAction: (String) -> Unit
){

    var cityName by rememberSaveable { mutableStateOf("") }
    var errorTextField by remember { mutableStateOf(false) }

    if (errorTextField && cityName.isNotBlank()){
        errorTextField = false
    }

    fun addAndHide(){
        if (cityName.isNotBlank()){
            addAction(cityName)
            hideAction()
        } else {
            errorTextField = true
        }
    }

    BasicAlertDialog(
        onDismissRequest = {},
        modifier = Modifier
            .background(
                color =  MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(sizeSpace20)
            )
            .padding(sizeSpace20)
    ) {
        Column{
            TextHead(stringResource(R.string.add_city))
            SpacerSmall()

            CityTextField(
                value = cityName,
                onValueChange = { newValue -> cityName = newValue },
                keyboardAction = { addAndHide() },
                errorText = errorTextField
            )
            SpacerMedium()

            Row(modifier = Modifier.fillMaxWidth()) {
                CancelButton(hideAction)
                AddButton { addAndHide() }
            }
        }
    }
}