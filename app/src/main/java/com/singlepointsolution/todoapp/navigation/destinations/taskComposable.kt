package com.singlepointsolution.todoapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.singlepointsolution.todoapp.ui.screens.task.TaskScreen
import com.singlepointsolution.todoapp.ui.viewmodels.TodoViewModel
import com.singlepointsolution.todoapp.utils.Action
import com.singlepointsolution.todoapp.utils.Constants

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