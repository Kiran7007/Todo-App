package com.singlepointsolution.todoapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun ToDoComposeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = lightColors(
            primary = Green200,
            primaryVariant = Green500
        ),
        content = content
    )
}