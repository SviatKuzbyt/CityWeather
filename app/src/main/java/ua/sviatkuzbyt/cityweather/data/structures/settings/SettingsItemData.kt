package ua.sviatkuzbyt.cityweather.data.structures.settings

import androidx.annotation.StringRes
import ua.sviatkuzbyt.cityweather.data.repositories.SettingsId

data class SettingsItemData(
    val id: SettingsId,
    @StringRes val label: Int,
    val setValue: String,
    val listOfValues: () -> List<String>,
)