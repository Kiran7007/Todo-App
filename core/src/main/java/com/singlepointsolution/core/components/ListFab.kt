package com.singlepointsolution.core.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ListFab(
    backGroundColor: Color,
    iconDescription: String,
    onFabClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = { onFabClicked() },
        backgroundColor = backGroundColor,
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = iconDescription,
            tint = Color.White
        )
    }
}