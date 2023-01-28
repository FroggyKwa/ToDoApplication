package com.example.todoapp.models.database.tasks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
class Task(
    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "Description", defaultValue = "")
    val description: String,
    @ColumnInfo(name = "Date", defaultValue = "")
    val date: String,
    @ColumnInfo(name = "isCompleted", defaultValue = false.toString())
    val isCompleted: Boolean = false,
    @PrimaryKey(true)
    val id: Int? = null

)