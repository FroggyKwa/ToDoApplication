package com.example.todoapp.utilities

import com.example.todoapp.models.database.tasks.Task
import java.io.Serializable

class TaskSerializer(
    val title: String,
    val description: String,
    var isCompleted: Boolean,
    val date: String,
    val id: Int? = null
) : Serializable {
    companion object {
        fun toTaskEntity(data: TaskSerializer): Task {
            return Task(
                data.title,
                data.description,
                data.date,
                data.isCompleted,
                data.id
            )
        }

        fun fromTaskEntity(data: Task): TaskSerializer {
            return TaskSerializer(
                data.title,
                data.description,
                data.isCompleted,
                data.date,
                data.id
            )
        }
    }
}
