package ua.sviatkuzbyt.cityweather.ui.elements.cities

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefreshLazyColumn(
    onRefresh: () -> Unit,
    content: LazyListScope.() -> Unit
){
    PullToRefreshBox(
        isRefreshing = false,
        onRefresh = onRefresh
    ){
        LazyColumn(Modifier.fillMaxSize()){
            content()
        }
    }
}