package com.example.todoapp.utilities

import com.example.todoapp.models.database.tasks.Task
import com.example.todoapp.models.database.DB
import com.example.todoapp.models.database.subtasks.Subtask

class Repository(
    private val db: DB
) {
    suspend fun addTask(task: Task) = db.getTasksDao().insert(task)

    suspend fun deleteTask(task: Task) = db.getTasksDao().delete(task)

    suspend fun updateTask(task: Task) = db.getTasksDao().update(task)

    fun getAllTasks() = db.getTasksDao().getAll()

    fun getAllImportant() = db.getTasksDao().getAllImportant()

    fun getAllCompletedTasks() = db.getTasksDao().getAllCompleted()

    fun getTaskById(id: Int) = db.getTasksDao().get(id)

    suspend fun addSubtask(subtask: Subtask) = db.getSubtasksDao().insert(subtask)

    suspend fun deleteSubtask(subtask: Subtask) = db.getSubtasksDao().delete(subtask)

    suspend fun updateSubtask(subtask: Subtask) = db.getSubtasksDao().update(subtask)

    fun getAllSubtasks(TaskID: Int) = db.getSubtasksDao().getAll(TaskID)

    fun getAllCompletedSubtasks(TaskID: Int) = db.getSubtasksDao().getAllCompleted(TaskID)

    fun getSubtaskById(id: Int) = db.getSubtasksDao().get(id)

}