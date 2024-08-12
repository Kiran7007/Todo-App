package com.singlepointsolution.todoapp.ui.screens.task

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.singlepointsolution.todoapp.R
import com.singlepointsolution.todoapp.ui.viewmodels.TodoViewModel
import com.singlepointsolution.todoapp.utils.Action
import com.singlepointsolution.todoapp.utils.TaskState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskScreen(todoViewModel: TodoViewModel, navigateToListScreen: (Action) -> Unit) {
    val title: String by todoViewModel.note
    val taskState: TaskState by todoViewModel.taskState
    val context = LocalContext.current

    BackHandler { navigateToListScreen(Action.NO_ACTION) }

    when (taskState) {
        TaskState.SUCCESS -> navigateToListScreen(Action.ADD)
        TaskState.ERROR -> navigateToListScreen(Action.ERROR)
        TaskState.VALIDATION_ERROR -> ShowToast(context)
        else -> {
            // do nothing
        }
    }

    Scaffold(topBar = {
        TaskAppBar(navigateToListScreen = navigateToListScreen)
    }, content = { _ ->
        if (taskState == TaskState.LOADING || taskState == TaskState.SUCCESS) {
            Loader()
        } else {
            TaskContent(note = title,
                onNoteChange = { todoViewModel.updateNote(it) },
                onAddButtonClick = { todoViewModel.addTask() })
        }
    })
}

@Composable
fun Loader() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { CircularProgressIndicator() }
    }
}

@Composable
fun ShowToast(context: Context) {
    Toast
        .makeText(context, stringResource(id = R.string.validation_error), Toast.LENGTH_SHORT)
        .show()
}