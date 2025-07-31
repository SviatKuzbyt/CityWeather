package ua.sviatkuzbyt.cityweather.ui.elements.other

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.cityweather.data.other.exceptionDescription

fun ViewModel.saveableCoroutineCall(
    message:  MutableStateFlow<Int?>? = null,
    errorHandler: suspend (Exception) -> Unit = {},
    finallyHandler: suspend () -> Unit = {},
    code: suspend () -> Unit
) = viewModelScope.launch(Dispatchers.IO){
    try {
        code()
    } catch (e: Exception){
        message?.value = exceptionDescription(e)
        errorHandler(e)
    } finally {
        finallyHandler()
    }
}