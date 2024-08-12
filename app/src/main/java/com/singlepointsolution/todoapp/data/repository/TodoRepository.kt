package com.singlepointsolution.todoapp.data.repository

import com.singlepointsolution.todoapp.data.dao.TodoDao
import com.singlepointsolution.todoapp.data.model.TodoData
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    val getAllTasks: Flow<List<TodoData>> = todoDao.getAllData()

    suspend fun addTask(todoData: TodoData) {
        todoDao.addTask(toDoTask = todoData)
    }

    fun searchDatabase(searchQuery: String): Flow<List<TodoData>> =
        todoDao.searchDatabase(searchQuery = searchQuery)
}