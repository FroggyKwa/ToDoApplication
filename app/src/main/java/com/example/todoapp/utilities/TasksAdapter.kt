package com.example.todoapp.utilities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TaskItemBinding
import com.example.todoapp.models.database.tasks.Task
import com.example.todoapp.viewmodels.TasksViewModel

class TasksAdapter(
    var tasks: List<Task>,
    private val viewModel: TasksViewModel,
    val clickListener: (Task) -> Unit
) :
    RecyclerView.Adapter<TasksAdapter.TasksListViewHolder>() {

    inner class TasksListViewHolder(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.listItem.setOnClickListener { clickListener(tasks[bindingAdapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
        return TasksListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksListViewHolder, position: Int) {
        holder.binding.apply {
            tvTaskTitle.text = tasks[position].title
            tvTaskDescription.text = tasks[position].description
            cbCompleted.isChecked = tasks[position].isCompleted
            cbCompleted.setOnCheckedChangeListener { _, isChecked ->
                val task = tasks[position]
                val edited = Task(
                    task.title,
                    task.description,
                    task.date,
                    isChecked,
                    task.id
                )
                viewModel.update(edited)
            }
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}