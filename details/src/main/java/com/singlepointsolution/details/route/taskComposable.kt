package com.singlepointsolution.details.route

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.singlepointsolution.common.sharedviewmodel.TodoViewModel
import com.singlepointsolution.common.utils.Action
import com.singlepointsolution.common.utils.Constants
import com.singlepointsolution.details.screen.TaskScreen

@ExperimentalAnimationApi
fun NavGraphBuilder.taskComposable(
    todoViewModel: TodoViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(route = Constants.TASK_SCREEN) { _ ->
        TaskScreen(
            todoViewModel,
            navigateToListScreen = navigateToListScreen
        )
    }
}