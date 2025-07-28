package ua.sviatkuzbyt.cityweather.ui.elements.cities

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.cityweather.ui.theme.sizeSpace16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefreshLazyColumn(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: LazyListScope.() -> Unit
){
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = sizeSpace16),
            content = content
        )
    }
}