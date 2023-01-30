package com.example.todoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TaskItemBinding
import com.example.todoapp.models.database.tasks.Task
import com.example.todoapp.utilities.GetResources
import com.example.todoapp.viewmodels.TasksViewModel

class TasksAdapter(
    var tasks: List<Task>,
    private val viewModel: TasksViewModel,
    val clickListener: (Task) -> Unit
) :
    RecyclerView.Adapter<TasksAdapter.TasksListViewHolder>() {
    private lateinit var res: GetResources

    inner class TasksListViewHolder(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.listItem.setOnClickListener { clickListener(tasks[bindingAdapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
        res = GetResources(parent.context)
        return TasksListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksListViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.apply {
            tvTaskTitle.text = tasks[position].title
            tvTaskDescription.text = tasks[position].description
            cbCompleted.isChecked = tasks[position].isCompleted
            cbCompleted.setOnCheckedChangeListener { _, isChecked ->
                val edited = Task(
                    task.title,
                    task.description,
                    task.date,
                    isChecked,
                    task.isImportant,
                    task.id
                )
                viewModel.update(edited)
            }
            btnImportant.setImageResource(res.getImportantButtonIcon(task.isImportant))
            btnImportant.setOnClickListener {
                task.isImportant = !task.isImportant
                val btnImportant = it as ImageButton
                btnImportant.setImageResource(res.getImportantButtonIcon(task.isImportant))
                viewModel.update(task)
            }
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}