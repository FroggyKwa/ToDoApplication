package com.example.todoapp.models.database.subtasks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Subtasks")
class Subtask(
    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "isCompleted", defaultValue = false.toString())
    var isCompleted: Boolean = false,
    @ColumnInfo(name = "TaskID")
    val taskID: Int,
    @PrimaryKey(true)
    val id: Int? = null
)
