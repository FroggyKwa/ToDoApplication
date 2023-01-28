package com.example.todoapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.utilities.Repository

@Suppress("UNCHECKED_CAST")
class SubtasksViewModelFactory(
    private val application: Application,
    private val repository: Repository
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SubtasksViewModel(application, repository) as T
    }
}