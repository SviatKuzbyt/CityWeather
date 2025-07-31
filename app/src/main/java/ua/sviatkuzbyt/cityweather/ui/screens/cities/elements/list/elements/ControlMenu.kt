package ua.sviatkuzbyt.cityweather.ui.screens.cities.elements.list.elements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.other.TextBasic
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace8

@Composable
fun ControlMenu(
    show: Boolean,
    onHide: () -> Unit,
    onDelete: () -> Unit,
    onMoveUp: () -> Unit
){
    DropdownMenu(
        expanded = show,
        onDismissRequest = onHide,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .background(MaterialTheme.colorScheme.surfaceContainer)

    ) {
        DropdownMenuButton(
            tittle = stringResource(R.string.delete),
            icon = R.drawable.ic_delete,
            onClick = {
                onHide()
                onDelete()
            }
        )

        DropdownMenuButton(
            tittle = stringResource(R.string.move_up),
            icon = R.drawable.ic_up,
            onClick = {
                onHide()
                onMoveUp()
            }
        )
    }
}

@Composable
fun DropdownMenuButton(
    tittle: String,
    @DrawableRes icon: Int? = null,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        leadingIcon = {
            if (icon != null){
                Icon(
                    imageVector = ImageVector.vectorResource(icon),
                    contentDescription = tittle,
                    Modifier.padding(start = sizeSpace8, end = sizeSpace8),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        text = { TextBasic(tittle) },
        onClick = onClick
    )
}