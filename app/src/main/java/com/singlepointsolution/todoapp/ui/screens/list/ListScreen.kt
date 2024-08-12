package com.singlepointsolution.todoapp.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.singlepointsolution.todoapp.R
import com.singlepointsolution.todoapp.ui.theme.fabBackgroundColor
import com.singlepointsolution.todoapp.ui.viewmodels.TodoViewModel
import com.singlepointsolution.todoapp.utils.Action
import com.singlepointsolution.todoapp.utils.SearchAppBarState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListScreen(
    action: Action,
    navigateToTaskScreen: () -> Unit,
    todoViewModel: TodoViewModel
) {
    LaunchedEffect(key1 = action) { todoViewModel.handleDatabaseActions(action = action) }

    val allTasks by todoViewModel.allTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by todoViewModel.searchAppBarState
    val searchTextState: String by todoViewModel.searchTextState
    val scaffoldState = rememberScaffoldState()

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        onComplete = { todoViewModel.action.value = it },
        action = action
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                todoViewModel = todoViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = { ListContent(requestState = allTasks) },
        floatingActionButton = { ListFab(onFabClicked = navigateToTaskScreen) }
    )
}

@Composable
fun ListFab(
    onFabClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = { onFabClicked() },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor,
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button_icon),
            tint = Color.White
        )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    onComplete: (Action) -> Unit,
    action: Action
) {
    val scope = rememberCoroutineScope()
    if (action != Action.NO_ACTION) {
        val message =
            stringResource(id = if (action == Action.ERROR) R.string.error_message else R.string.success_message)
        LaunchedEffect(key1 = action) {
            scope.launch { scaffoldState.snackbarHostState.showSnackbar(message = message) }
            onComplete(Action.NO_ACTION)
        }
    }
}