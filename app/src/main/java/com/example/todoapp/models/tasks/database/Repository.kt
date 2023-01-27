package com.example.todoapp.models.tasks.database

import com.example.todoapp.models.tasks.Task

class Repository(
    private val db: DB
) {
    suspend fun add(task: Task) = db.getTasksDao().insert(task)

    suspend fun delete(task: Task) = db.getTasksDao().delete(task)

    suspend fun update(task: Task) = db.getTasksDao().update(task)

    fun getAllTasks() = db.getTasksDao().getAll()

    fun getAllCompletedTasks() = db.getTasksDao().getAllCompleted()

    fun getById(id: Int) = db.getTasksDao().get(id)

}