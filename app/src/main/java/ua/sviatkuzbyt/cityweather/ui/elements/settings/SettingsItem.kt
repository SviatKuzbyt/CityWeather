package ua.sviatkuzbyt.cityweather.ui.elements.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.data.structures.settings.SettingsItemData
import ua.sviatkuzbyt.cityweather.ui.elements.basic.basicClick
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.ItemList
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextBasic
import ua.sviatkuzbyt.cityweather.ui.elements.basic.elements.TextBasicSmall
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace20
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace8

@Composable
fun SettingsItem(
    data: SettingsItemData,
    onChangeValue: (String) -> Unit
){
    var isShowList by remember {
        mutableStateOf(false)
    }

    ItemList(
        Modifier.basicClick { isShowList = true }
    ) {
        TextBasic(
            text = stringResource(data.label),
            modifier = Modifier.padding(start = sizeSpace20).weight(1f)
        )

        TextBasicSmall(
            text = data.setValue,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Box{
            OpenIcon(isShowList)
            SettingsList(
                data = data.listOfValues,
                isShow = isShowList,
                onDismiss = {isShowList = false},
                onClickElement = onChangeValue
            )
        }
    }
}

@Composable
private fun OpenIcon(isRotate: Boolean) {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_open_more),
        contentDescription = stringResource(R.string.open_settings),
        modifier = Modifier
            .padding(start = sizeSpace8, end = sizeSpace20)
            .width(10.dp)
            .rotate(if(isRotate) 180f else 0f)
        ,
        tint = MaterialTheme.colorScheme.onSecondaryContainer
    )
}

@Composable
private fun SettingsList(
    data: () -> List<String>,
    isShow: Boolean,
    onDismiss: () -> Unit,
    onClickElement: (String) -> Unit
){
    DropdownMenu(
        expanded = isShow,
        onDismissRequest = onDismiss,
        offset = DpOffset(-sizeSpace8, sizeSpace16)

    ) {
        data.invoke().forEach {
            DropdownMenuItem(
                text = { TextBasic(it) },
                onClick = {
                    onClickElement(it)
                    onDismiss()
                }
            )
        }
    }
}