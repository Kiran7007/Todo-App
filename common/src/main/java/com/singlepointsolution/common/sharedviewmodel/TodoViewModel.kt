package com.singlepointsolution.common.sharedviewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepointsolution.common.model.TodoData
import com.singlepointsolution.common.repository.TodoRepository
import com.singlepointsolution.common.utils.Action
import com.singlepointsolution.common.utils.Constants
import com.singlepointsolution.common.utils.RequestState
import com.singlepointsolution.common.utils.SearchAppBarState
import com.singlepointsolution.common.utils.TaskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val note: MutableState<String> = mutableStateOf("")

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    val taskState: MutableState<TaskState> = mutableStateOf(TaskState.IDLE)

    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<TodoData>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<TodoData>>> = _allTasks

    init {
        getAllTasks()
    }

    fun getAllTasks() {
        try {
            viewModelScope.launch {
                if (searchTextState.value.isEmpty()) {
                    _allTasks.value = RequestState.Loading
                    repository.getAllTasks
                } else {
                    delay(2000)
                    _allTasks.value = RequestState.Loading
                    repository.searchDatabase(searchQuery = "%${searchTextState.value}%")
                }.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTasks.value = RequestState.Error(e)
        }
    }

    fun addTask() {
        when {
            isEmptyField() -> taskState.value = TaskState.VALIDATION_ERROR
            isErrorCase() -> taskState.value = TaskState.ERROR
            else -> {
                viewModelScope.launch(Dispatchers.IO) {
                    taskState.value = TaskState.LOADING
                    delay(3000)
                    repository.addTask(todoData = TodoData(note = note.value))
                    taskState.value = TaskState.SUCCESS
                }
            }
        }
    }

    fun updateNote(newNote: String) {
        note.value = newNote
    }

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD, Action.ERROR -> {
                taskState.value = TaskState.IDLE
                searchAppBarState.value = SearchAppBarState.CLOSED
                note.value = ""
            }

            else -> {
                // do nothing
            }
        }
    }

    private fun isEmptyField() = note.value.isEmpty()
    private fun isErrorCase() = note.value.equals(Constants.ERROR_CASE, true)
}