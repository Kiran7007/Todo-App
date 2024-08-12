package com.singlepointsolution.todoapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.singlepointsolution.todoapp.navigation.destinations.listComposable
import com.singlepointsolution.todoapp.navigation.destinations.taskComposable
import com.singlepointsolution.todoapp.ui.viewmodels.TodoViewModel
import com.singlepointsolution.todoapp.utils.Constants

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(navController: NavHostController, todoViewModel: TodoViewModel) {
    val screen = remember(navController) { Screens(navController = navController) }

    NavHost(navController = navController, startDestination = Constants.LIST_SCREEN) {
        listComposable(navigateToTaskScreen = screen.list, todoViewModel = todoViewModel)
        taskComposable(navigateToListScreen = screen.task, todoViewModel = todoViewModel)
    }
}
