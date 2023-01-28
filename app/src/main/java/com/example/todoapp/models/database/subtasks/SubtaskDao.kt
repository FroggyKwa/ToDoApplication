package com.example.todoapp.models.database.subtasks

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubtaskDao {
    @Query("SELECT * FROM Subtasks WHERE TaskID = :TaskID")
    fun getAll(TaskID: Int): LiveData<List<Subtask>>

    @Query("SELECT * FROM Subtasks WHERE isCompleted = 1 AND TaskID = :TaskID")
    fun getAllCompleted(TaskID: Int): LiveData<List<Subtask>>

    @Query("SELECT * FROM Subtasks WHERE id = :id")
    fun get(id: Int): LiveData<Subtask>

    @Delete
    abstract suspend fun delete(subtask: Subtask)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(subtask: Subtask)

    @Update
    abstract suspend fun update(subtask: Subtask)

}