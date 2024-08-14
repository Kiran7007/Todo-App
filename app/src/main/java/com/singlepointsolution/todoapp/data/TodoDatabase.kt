package com.singlepointsolution.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.singlepointsolution.common.dao.TodoDao
import com.singlepointsolution.common.model.TodoData

@Database(entities = [TodoData::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}