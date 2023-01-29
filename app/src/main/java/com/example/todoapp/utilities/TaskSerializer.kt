package com.example.todoapp.utilities

import com.example.todoapp.models.database.tasks.Task
import java.io.Serializable

class TaskSerializer(
    var title: String,
    var description: String,
    var isCompleted: Boolean,
    var isImportant: Boolean,
    var date: String,
    val id: Int? = null
) : Serializable {
    companion object {
        fun toTaskEntity(data: TaskSerializer): Task {
            return Task(
                data.title,
                data.description,
                data.date,
                data.isCompleted,
                data.isImportant,
                data.id
            )
        }

        fun fromTaskEntity(data: Task): TaskSerializer {
            return TaskSerializer(
                data.title,
                data.description,
                data.isCompleted,
                data.isImportant,
                data.date,
                data.id
            )
        }
    }
}
