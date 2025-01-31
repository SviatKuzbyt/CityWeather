package ua.sviatkuzbyt.cityweather.data.structures.settings

import androidx.annotation.StringRes

data class SettingsItemData(
    val id: Int,
    @StringRes val label: Int,
    val setValue: String,
    val listOfValues: () -> List<String>,
)