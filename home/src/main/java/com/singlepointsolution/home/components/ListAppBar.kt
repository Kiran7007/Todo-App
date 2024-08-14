package com.singlepointsolution.home.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.singlepointsolution.common.sharedviewmodel.TodoViewModel
import com.singlepointsolution.common.utils.SearchAppBarState
import com.singlepointsolution.core.components.SearchAppBar
import com.singlepointsolution.home.R

@Composable
fun ListAppBar(
    todoViewModel: TodoViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = {
                    todoViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                }
            )
        }

        else -> {
            SearchAppBar(
                text = searchTextState,
                title = stringResource(R.string.search),
                onTextChange = { newText ->
                    todoViewModel.searchTextState.value = newText
                    todoViewModel.getAllTasks()
                },
                onCloseClicked = {
                    todoViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    todoViewModel.searchTextState.value = ""
                    todoViewModel.getAllTasks()
                }
            )
        }
    }
}

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.tasks),
                color = MaterialTheme.colors.background
            )
        },
        actions = { SearchAction(onSearchClicked = onSearchClicked) },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}

@Composable
fun SearchAction(
    title: String = "",
    onSearchClicked: () -> Unit
) {
    IconButton(
        onClick = { onSearchClicked() }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = title,
            tint = MaterialTheme.colors.background
        )
    }
}


@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(onSearchClicked = {})
}

@Composable
@Preview
private fun SearchAppBarPreview() {
    SearchAppBar(
        text = "",
        title = "",
        onTextChange = {},
        onCloseClicked = {}
    )
}
