package com.singlepointsolution.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.singlepointsolution.common.sharedviewmodel.TodoViewModel
import com.singlepointsolution.common.utils.Constants
import com.singlepointsolution.details.route.taskComposable
import com.singlepointsolution.home.route.listComposable
import com.singlepointsolution.navigation.feature.Screens

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
