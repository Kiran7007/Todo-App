package com.singlepointsolution.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.singlepointsolution.todoapp.data.dao.TodoDao
import com.singlepointsolution.todoapp.data.model.TodoData

@Database(entities = [TodoData::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}