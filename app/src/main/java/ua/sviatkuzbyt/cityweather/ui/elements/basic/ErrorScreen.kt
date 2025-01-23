package ua.sviatkuzbyt.cityweather.ui.elements.basic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.cityweather.R
import ua.sviatkuzbyt.cityweather.ui.theme.buttonHeight

@Composable
fun ErrorScreen(onRetryClick: () -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_error),
            contentDescription = stringResource(R.string.error),
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.size(56.dp)
        )
        SpaceMedium()

        CenterBasicText(stringResource(R.string.error_load))
        Spacer(Modifier.height(32.dp))

        RetryButton { onRetryClick() }
    }
}

@Composable
private fun RetryButton(onClick: () -> Unit){
    Button(
        onClick = onClick,
        shape = containerShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = Modifier.heightIn(buttonHeight),
        contentPadding = PaddingValues(horizontal = 32.dp)
    ) {
        BasicText(stringResource(R.string.retry))
    }
}


@Preview
@Composable
fun ErrorScreenPreview(){
    ErrorScreen {}
}