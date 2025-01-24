package ua.sviatkuzbyt.cityweather.ui.elements.cities.add

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextBasic
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.shapeContainer
import ua.sviatkuzbyt.cityweather.ui.theme.Red

@Composable
fun CityTextField(
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