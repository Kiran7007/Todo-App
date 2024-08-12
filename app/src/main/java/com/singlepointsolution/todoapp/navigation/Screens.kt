package com.singlepointsolution.todoapp.navigation

import androidx.navigation.NavHostController
import com.singlepointsolution.todoapp.utils.Action
import com.singlepointsolution.todoapp.utils.Constants

class Screens(navController: NavHostController) {

    val list: () -> Unit = {
        navController.navigate(route = "task/${Action.NO_ACTION}")
    }

    val task: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action}") {
            popUpTo(Constants.LIST_SCREEN) { inclusive = true }
        }
    }
}