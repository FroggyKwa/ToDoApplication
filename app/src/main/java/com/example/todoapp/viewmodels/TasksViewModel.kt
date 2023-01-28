package com.example.todoapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.models.tasks.Task
import com.example.todoapp.utilities.Repository
import kotlinx.coroutines.launch

class TasksViewModel(application: Application, private val repository: Repository) :
    AndroidViewModel(application) {
    fun add(task: Task) {
        viewModelScope.launch {
            repository.add(task)
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    fun update(task: Task) {
        viewModelScope.launch {
            repository.update(task)
        }
    }

    fun get(id: Int) = repository.getById(id)

    fun getAll() = repository.getAllTasks()
}