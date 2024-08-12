package com.singlepointsolution.todoapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.singlepointsolution.todoapp.ui.viewmodels.TodoViewModel
import com.singlepointsolution.todoapp.utils.Action
import com.singlepointsolution.todoapp.utils.Constants
import com.singlepointsolution.todoapp.utils.toAction
import com.singlepointsolution.todoapp.ui.screens.list.ListScreen

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: () -> Unit,
    todoViewModel: TodoViewModel
) {
    composable(route = Constants.LIST_SCREEN) { navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(Constants.LIST_ARGUMENT_KEY).toAction()
        var myAction by rememberSaveable { mutableStateOf(Action.NO_ACTION) }

        LaunchedEffect(key1 = action) {
            if (action != myAction) {
                myAction = action
                todoViewModel.action.value = action
            }
        }

        val databaseAction by todoViewModel.action

        ListScreen(
            action = databaseAction,
            navigateToTaskScreen = navigateToTaskScreen,
            todoViewModel = todoViewModel
        )
    }
}