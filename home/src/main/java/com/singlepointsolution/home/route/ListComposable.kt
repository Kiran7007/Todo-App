package com.singlepointsolution.home.route

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.singlepointsolution.common.sharedviewmodel.TodoViewModel
import com.singlepointsolution.common.utils.Action
import com.singlepointsolution.common.utils.Constants
import com.singlepointsolution.common.utils.toAction
import com.singlepointsolution.home.screen.ListScreen

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