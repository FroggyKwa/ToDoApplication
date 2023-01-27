package com.example.todoapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.models.Task

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    fun add(task: Task) {
        //TODO: not implemented
    }

    fun delete(task: Task) {
        //TODO: not implemented
    }

    fun update(task: Task) {
        //TODO: not implemented
    }

    fun get(id: Int) {
        //TODO: not implemented
    }

    fun getAll(): List<Task> {
        return listOf(
            Task("Testing data123", "Description of this task", "20.10.23"),
            Task("Testing data123", "Description of this task", "20.10.23"),
            Task("Testing data123", "Description of this task", "20.10.23"),
            Task("Testing data123", "Description of this task", "20.10.23"),
            Task("Testing data123", "Description of this task", "20.10.23"),
            Task("Testing data123", "Description of this task", "20.10.23"),
            Task("Testing data123", "Description of this task", "20.10.23"),
            Task("Testing data123", "Description of this task", "20.10.23"),
            Task("Testing data123", "Description of this task", "20.10.23"),
            Task("Testing data123", "Description of this task", "20.10.23"),
        )
        //TODO: not implemented
    }
}