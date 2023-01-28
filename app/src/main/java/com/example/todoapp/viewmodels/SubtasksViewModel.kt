package com.example.todoapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.models.database.subtasks.Subtask
import com.example.todoapp.models.database.tasks.Task
import com.example.todoapp.utilities.Repository
import kotlinx.coroutines.launch

class SubtasksViewModel(application: Application, private val repository: Repository) :
    AndroidViewModel(application) {
    fun add(subtask: Subtask) {
        viewModelScope.launch {
            repository.addSubtask(subtask)
        }
    }

    fun delete(subtask: Subtask) {
        viewModelScope.launch {
            repository.deleteSubtask(subtask)
        }
    }

    fun update(subtask: Subtask) {
        viewModelScope.launch {
            repository.updateSubtask(subtask)
        }
    }

    fun get(id: Int) = repository.getSubtaskById(id)

    fun getAll(TaskID: Int) = repository.getAllSubtasks(TaskID)
}