package com.example.todoapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.models.tasks.database.Repository

@Suppress("UNCHECKED_CAST")
class TasksViewModelFactory(
    private val application: Application,
    private val repository: Repository
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(application, repository) as T
    }
}