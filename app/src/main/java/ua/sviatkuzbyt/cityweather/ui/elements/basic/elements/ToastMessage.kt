package ua.sviatkuzbyt.cityweather.ui.elements.basic.elements

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun ToastMessage(
    @StringRes message: Int?,
    onClearMessage: () -> Unit
){
    val context = LocalContext.current
    LaunchedEffect(message) {
        message?.let {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            onClearMessage()
        }
    }
}