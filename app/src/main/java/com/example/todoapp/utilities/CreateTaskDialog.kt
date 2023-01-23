package com.example.todoapp.utilities

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.todoapp.databinding.CreateTaskDialogBinding

class CreateTaskDialog : AppCompatDialogFragment() {
    lateinit var dialogInterface: CreateTaskDialogInterface
    private var _binding: CreateTaskDialogBinding? = null
    private val binding get() = _binding!!
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = CreateTaskDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())

        builder.setView(binding.root)
            .setTitle("Add a new task")
            .setNegativeButton("Cancel") {dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Add") {dialog, _ ->
                val title = binding.etTitle.text.toString()
                val description = binding.etDescription.text.toString()
                val date = binding.tvDate.text.toString()
                dialogInterface.addTask(title, description, date)
                dialog.dismiss()
            }

        return builder.create()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dialogInterface = context as CreateTaskDialogInterface
    }

    public interface CreateTaskDialogInterface {
        fun addTask(title: String, description: String, date: String)
    }
}