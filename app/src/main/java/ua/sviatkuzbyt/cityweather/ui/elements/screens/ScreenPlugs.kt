package ua.sviatkuzbyt.cityweather.ui.elements.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.elements.other.SpacerMedium
import ua.sviatkuzbyt.cityweather.ui.elements.other.TextBasicCenter
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace36
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace48

@Composable
fun LazyItemScope.TextPlugList(
    text: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier
){
    TextPlugBasic(
        text = text,
        icon = icon,
        modifier = modifier.fillParentMaxSize()
    )
}

@Composable
fun TextPlug(
    text: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier
){
    TextPlugBasic(
        text = text,
        icon = icon,
        modifier = modifier.fillMaxSize().padding(horizontal = sizeSpace36)
    )
}

@Composable
fun LoadPlug(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.width(sizeSpace36),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceContainer,
            strokeWidth = 2.dp
        )
    }
}

@Composable
private fun TextPlugBasic(
    text: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = stringResource(R.string.icon),
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.size(sizeSpace48)
        )

        SpacerMedium()

        TextBasicCenter(
            text = text,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}