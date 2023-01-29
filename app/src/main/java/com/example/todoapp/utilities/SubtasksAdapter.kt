package com.example.todoapp.utilities

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.SubtaskItemBinding
import com.example.todoapp.models.database.subtasks.Subtask
import com.example.todoapp.viewmodels.SubtasksViewModel

class SubtasksAdapter(
    var subtasks: List<Subtask>,
    private val viewModel: SubtasksViewModel
) : RecyclerView.Adapter<SubtasksAdapter.SubtasksListViewHolder>() {
    inner class SubtasksListViewHolder(
        val binding: SubtaskItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtasksListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SubtaskItemBinding.inflate(layoutInflater, parent, false)
        return SubtasksListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubtasksListViewHolder, position: Int) {
        holder.binding.apply {
            tvSubtaskTitle.setText(subtasks[position].title)
            btnSave.setOnClickListener {
                val subtask = subtasks[holder.bindingAdapterPosition]
                viewModel.update(
                    Subtask(
                        tvSubtaskTitle.text.toString(),
                        cbCompletedSub.isChecked,
                        subtask.taskID,
                        subtask.id
                    )
                )
            }
            tvSubtaskTitle.setOnFocusChangeListener { view, hasFocus ->
                fun Context.hideKeyboard(view: View) {
                    val inputMethodManager =
                        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                }

                btnSave.isVisible = hasFocus
                if (!hasFocus)
                    view.context.hideKeyboard(view)

            }
            cbCompletedSub.setOnCheckedChangeListener(null)
            cbCompletedSub.isChecked = false
            cbCompletedSub.setOnClickListener {
                viewModel.delete(subtasks[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int {
        return subtasks.size
    }
}