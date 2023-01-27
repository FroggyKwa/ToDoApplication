package com.example.todoapp.models.tasks.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.models.tasks.Task

@Dao
interface TasksDao {
    @Query("SELECT * FROM Tasks")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM Tasks WHERE isCompleted = 1")
    fun getAllCompleted(): LiveData<List<Task>>

    @Query("SELECT * FROM Tasks WHERE id = :id")
    fun get(id: Int): LiveData<Task>

    @Delete
    abstract suspend fun delete(task: Task)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(task: Task)

    @Update
    abstract suspend fun update(task: Task)

}