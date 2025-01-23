package ua.sviatkuzbyt.cityweather.ui.elements.cities.add

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.basic.BasicText
import ua.sviatkuzbyt.cityweather.ui.elements.basic.containerShape

@Composable
fun CityTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardAction: () -> Unit
){
    val colors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        unfocusedBorderColor = MaterialTheme.colorScheme.surfaceContainer,
        focusedBorderColor = MaterialTheme.colorScheme.surfaceContainer,
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = containerShape,
        placeholder = {
            BasicText(
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
        )
    )
}