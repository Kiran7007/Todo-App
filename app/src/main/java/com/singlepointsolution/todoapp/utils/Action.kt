package com.singlepointsolution.todoapp.utils

enum class Action {
    ADD,
    ERROR,
    NO_ACTION
}

enum class TaskState {
    IDLE,
    SUCCESS,
    ERROR,
    VALIDATION_ERROR,
    LOADING
}

fun String?.toAction(): Action =
    if (this.isNullOrEmpty()) Action.NO_ACTION else Action.valueOf(this)
