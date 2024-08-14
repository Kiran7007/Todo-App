package com.singlepointsolution.home.components

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.singlepointsolution.common.model.TodoData
import com.singlepointsolution.common.utils.RequestState
import com.singlepointsolution.core.components.EmptyContent
import com.singlepointsolution.core.theme.MEDIUM_PADDING
import com.singlepointsolution.core.theme.TASK_ITEM_ELEVATION
import com.singlepointsolution.home.R

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListContent(
    requestState: RequestState<List<TodoData>>
) {
    if (requestState is RequestState.Success) {
        HandleListContent(tasks = requestState.data)
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun HandleListContent(
    tasks: List<TodoData>
) {
    if (tasks.isEmpty()) EmptyContent(stringResource(R.string.empty_content))
    else DisplayTasks(tasks = tasks)
}

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun DisplayTasks(tasks: List<TodoData>) {
    LazyColumn {
        items(items = tasks, key = { task -> task.id }) { task ->
            var itemAppeared by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true) { itemAppeared = true }
            TaskItem(toDoTask = task)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TaskItem(toDoTask: TodoData) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION
    ) {
        Column(
            modifier = Modifier
                .padding(all = MEDIUM_PADDING)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = toDoTask.note,
                style = MaterialTheme.typography.h6,
                maxLines = 1
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(
        toDoTask = TodoData(
            0,
            "Tasks"
        )
    )
}
