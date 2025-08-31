import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.msk.core.design_system.R
import com.msk.design_system.components.CineJetErrorDialog
import com.msk.design_system.components.CineJetText
import com.msk.design_system.components.SwipeToDeleteItem

@Composable
fun <T : Any> PaginatedContent(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<T>,
    isSwipeToDelete: Boolean = false,
    onDelete: (T) -> Unit = {},
    itemContent: @Composable (T) -> Unit,
    emptyView: @Composable () -> Unit = { CineJetEmptyView() },
    loadingView: @Composable () -> Unit = { CineJetLoadingView() },
    showErrorDialog: (String)-> Unit = {},
) {
    val isRefreshing = items.loadState.refresh is LoadState.Loading
    val isAppending = items.loadState.append is LoadState.Loading
    val refreshError = items.loadState.refresh as? LoadState.Error
    val appendError = items.loadState.append as? LoadState.Error

    Box(modifier = modifier.fillMaxSize()) {
        if(refreshError != null ) showErrorDialog(stringResource(R.string.offline_mode_message))

        when {
            isRefreshing -> loadingView()
            items.itemCount == 0 -> emptyView()
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(items.itemCount,key = { index ->
                        val item = items[index]
                        item?.hashCode() ?: index
                    }) { index ->
                        val item = items[index] ?: return@items
                       itemContent(item)

                    }

                    item {
                        when {
                            isAppending -> loadingView()
                            appendError != null ->  showErrorDialog(stringResource(R.string.offline_mode_message))
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun CineJetLoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun CineJetEmptyView() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CineJetText(
            text = stringResource(R.string.no_content_found),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}