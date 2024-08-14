package com.singlepointsolution.home.screen

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import com.singlepointsolution.common.sharedviewmodel.TodoViewModel
import com.singlepointsolution.common.utils.Action
import com.singlepointsolution.common.utils.SearchAppBarState
import com.singlepointsolution.core.components.ListFab
import com.singlepointsolution.home.R
import com.singlepointsolution.home.components.ListAppBar
import com.singlepointsolution.home.components.ListContent
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
        successMessage = stringResource(R.string.success_message),
        errorMessage = stringResource(R.string.error_message),
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
        floatingActionButton = {
            ListFab(
                backGroundColor = MaterialTheme.colors.primary,
                iconDescription = stringResource(R.string.add_button_icon),
                onFabClicked = navigateToTaskScreen
            )
        }
    )
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    successMessage: String,
    errorMessage: String,
    onComplete: (Action) -> Unit,
    action: Action
) {
    val scope = rememberCoroutineScope()
    if (action != Action.NO_ACTION) {
        val message = if (action == Action.ERROR) errorMessage else successMessage
        LaunchedEffect(key1 = action) {
            scope.launch { scaffoldState.snackbarHostState.showSnackbar(message = message) }
            onComplete(Action.NO_ACTION)
        }
    }
}