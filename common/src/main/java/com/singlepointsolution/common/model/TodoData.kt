package com.singlepointsolution.common.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.singlepointsolution.common.utils.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class TodoData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val note: String
)