package com.example.todoapp.utilities

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.example.todoapp.models.database.tasks.Task
import com.example.todoapp.views.EditTaskActivity

object TaskUtils {
    val TAB_NAMES = listOf("All tasks", "Important tasks", "Completed tasks")
    fun openEditTaskActivity(task: Task, context: Context, resultLauncher: ActivityResultLauncher<Intent>) {
        Intent(context, EditTaskActivity::class.java).also { intent ->
            intent.putExtra("Task", TaskSerializer.fromTaskEntity(task))
            resultLauncher.launch(intent)
        }
    }
}