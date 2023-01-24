package com.example.todoapp.models


data class Task(
    val title: String,
    val description: String,
    val date: String,
    val completed: Boolean = false,
)
