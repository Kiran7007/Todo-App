package com.singlepointsolution.common.repository

import com.singlepointsolution.common.dao.TodoDao
import com.singlepointsolution.common.model.TodoData
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