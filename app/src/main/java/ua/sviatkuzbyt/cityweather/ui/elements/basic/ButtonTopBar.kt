package ua.sviatkuzbyt.cityweather.ui.elements.basic

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import ua.sviatkuzbyt.cityweather.ui.theme.Gray
import ua.sviatkuzbyt.cityweather.ui.theme.buttonHeight

//Кнопки лише з іконкою

@Composable
fun ButtonTopBar(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    @StringRes contentDescription: Int,
    onClick: () -> Unit
){
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    Icon(
        imageVector = ImageVector.vectorResource(imageId),
        contentDescription = stringResource(contentDescription),
        tint = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
            .size(buttonHeight)
            .clickable(
                onClick = onClick,
                enabled = true,
                role = Role.Button,
                interactionSource = interactionSource,

                indication = androidx.compose.material.ripple.rememberRipple(
                    bounded = false,
                    radius = buttonHeight / 2,
                    color = Gray
                )
            )
    )
}