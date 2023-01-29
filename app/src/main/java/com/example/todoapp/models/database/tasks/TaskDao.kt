package com.example.todoapp.models.database.tasks

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TasksDao {
    @Query("SELECT * FROM Tasks")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM Tasks WHERE isCompleted = 1")
    fun getAllCompleted(): LiveData<List<Task>>

    @Query("SELECT * FROM Tasks WHERE isImportant = 1")
    fun getAllImportant(): LiveData<List<Task>>

    @Query("SELECT * FROM Tasks WHERE id = :id")
    fun get(id: Int): LiveData<Task>

    @Delete
    suspend fun delete(task: Task)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

}