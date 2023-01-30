package com.example.todoapp.utilities

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.constraintlayout.widget.Group
import com.example.todoapp.R
import com.example.todoapp.databinding.CreateTaskDialogBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class CreateTaskDialog : AppCompatDialogFragment() {
    private lateinit var dialogInterface: CreateTaskDialogInterface
    private var _binding: CreateTaskDialogBinding? = null
    private val binding get() = _binding!!
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = CreateTaskDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())

        builder.setView(binding.root)
            .setTitle(R.string.add_a_new_task)
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.add) { dialog, _ ->
                val title = binding.etTitle.text.toString()
                val description = binding.etDescription.text.toString()
                val date = binding.tvDate.text.toString()
                dialogInterface.addTask(title, description, date)
                dialog.dismiss()
            }

        val dialog = builder.create()
        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = !s.isNullOrEmpty() && s.isNotBlank()
            }
        })
        binding.group.setAllOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, yearPicked, monthPicked, dayPicked ->
                    calendar.set(Calendar.YEAR, yearPicked)
                    calendar.set(Calendar.MONTH, monthPicked)
                    calendar.set(Calendar.DAY_OF_MONTH, dayPicked)
                    val formatted =
                        SimpleDateFormat("dd MMMM yyyy", Locale.US).format(calendar.time)
                    binding.tvDate.text = formatted
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
        }
        return dialog
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dialogInterface = context as CreateTaskDialogInterface
    }

    fun Group.setAllOnClickListener(listener: View.OnClickListener?) {
        referencedIds.forEach { id ->
            rootView.findViewById<View>(id).setOnClickListener(listener)
        }
    }

    interface CreateTaskDialogInterface {
        fun addTask(title: String, description: String, date: String)
    }
}