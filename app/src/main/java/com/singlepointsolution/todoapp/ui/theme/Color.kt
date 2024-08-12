package com.singlepointsolution.todoapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val MediumGray = Color(0xFF9C9C9C)
val Green200 = Color(0xFF009400)
val Green500 = Color(0xFF006100)

val Colors.topAppBarContentColor: Color
    @Composable
    get() = Color.White

val Colors.topAppBarBackGroundColor: Color
    @Composable
    get() = Green200

val Colors.fabBackgroundColor: Color
    @Composable
    get() = Green500
