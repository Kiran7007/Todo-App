package com.singlepointsolution.common.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.singlepointsolution.common.model.TodoData
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): Flow<List<TodoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(toDoTask: TodoData)

    @Query("SELECT * FROM todo_table WHERE note LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<TodoData>>
}