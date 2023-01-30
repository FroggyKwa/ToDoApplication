package com.example.todoapp.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.PopupMenu
import androidx.activity.result.ActivityResultLauncher
import com.example.todoapp.R
import com.example.todoapp.adapters.TasksAdapter
import com.example.todoapp.models.database.tasks.Task
import com.example.todoapp.views.EditTaskActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object TaskUtils {
    val TAB_NAMES = listOf("All tasks", "Important tasks", "Completed tasks")
    fun openEditTaskActivity(
        task: Task,
        context: Context,
        resultLauncher: ActivityResultLauncher<Intent>
    ) {
        Intent(context, EditTaskActivity::class.java).also { intent ->
            intent.putExtra("Task", TaskSerializer.fromTaskEntity(task))
            resultLauncher.launch(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showPopup(adapter: TasksAdapter, view: View, context: Context) {
        val popup = PopupMenu(context, view)
        popup.inflate(R.menu.more_options_menu)
        popup.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.miSortByName -> {
                    adapter.tasks = adapter.tasks.sortedBy { task -> task.title }
                    adapter.notifyDataSetChanged()
                    true
                }
                R.id.miSortByDate -> {
                    val format = DateTimeFormatter.ofPattern("dd MMMM yyyy")
                    adapter.tasks = adapter.tasks.sortedBy { task -> LocalDate.parse(task.date, format) }
                    adapter.notifyDataSetChanged()
                    true
                }
                R.id.miSortByImportance -> {
                    adapter.tasks = adapter.tasks.sortedBy { task -> !task.isImportant }
                    adapter.notifyDataSetChanged()
                    true
                }
                else -> {
                    false
                }
            }
        }
        popup.show()
    }
}