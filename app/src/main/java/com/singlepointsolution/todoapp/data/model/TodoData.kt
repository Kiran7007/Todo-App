package com.singlepointsolution.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.singlepointsolution.todoapp.utils.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class TodoData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val note: String
)