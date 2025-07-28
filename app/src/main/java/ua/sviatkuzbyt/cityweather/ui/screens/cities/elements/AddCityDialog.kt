@file:OptIn(ExperimentalMaterial3Api::class)

package ua.sviatkuzbyt.cityweather.ui.screens.cities.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.other.SpacerMedium
import ua.sviatkuzbyt.cityweather.ui.elements.other.SpacerSmall
import ua.sviatkuzbyt.cityweather.ui.elements.other.TextBasic
import ua.sviatkuzbyt.cityweather.ui.elements.other.TextHead
import ua.sviatkuzbyt.cityweather.ui.elements.other.shapeContainer
import ua.sviatkuzbyt.cityweather.ui.theme.Red
import ua.sviatkuzbyt.cityweather.ui.theme.WhiteTransparent
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace20
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace48
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace8

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

@Composable
private fun CityTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardAction: () -> Unit,
    errorText: Boolean
){
    val colors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        unfocusedBorderColor = MaterialTheme.colorScheme.surfaceContainer,
        focusedBorderColor = MaterialTheme.colorScheme.surfaceContainer,
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        errorContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        errorBorderColor = Red,
        errorCursorColor = Red
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = shapeContainer,
        placeholder = {
            TextBasic(
                text = stringResource(R.string.enter_city),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        colors = colors,
        modifier = Modifier.fillMaxWidth(),
        keyboardActions = KeyboardActions {
            keyboardAction()
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Search
        ),
        isError = errorText
    )
}

@Composable
private fun RowScope.AddButton(
    onClick: () -> Unit = {}
){
    Button(
        onClick = onClick,
        shape = shapeContainer,
        modifier = Modifier
            .height(sizeSpace48)
            .weight(1f)
            .padding(start = sizeSpace8),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = WhiteTransparent
        )
    ) {
        TextHead(
            text = stringResource(R.string.add),
            color = Color.White
        )
    }
}

@Composable
private fun RowScope.CancelButton(
    onClick: () -> Unit = {}
){
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(sizeSpace48)
            .weight(1f)
            .padding(end = sizeSpace8),
        shape = shapeContainer,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        TextBasic(stringResource(R.string.cancel))
    }
}