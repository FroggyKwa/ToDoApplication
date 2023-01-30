package com.example.todoapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.models.database.tasks.Task
import com.example.todoapp.utilities.Repository
import kotlinx.coroutines.launch

class TasksViewModel(application: Application, private val repository: Repository) :
    AndroidViewModel(application) {
    fun add(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun update(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun get(id: Int) = repository.getTaskById(id)

    fun getAll() = repository.getAllTasks()

    fun getAllImportant() = repository.getAllImportant()

    fun getAllCompleted() = repository.getAllCompletedTasks()
}