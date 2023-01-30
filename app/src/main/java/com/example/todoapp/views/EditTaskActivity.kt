package com.example.todoapp.views

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.ActivityEditTaskBinding
import com.example.todoapp.models.database.DB
import com.example.todoapp.models.database.subtasks.Subtask
import com.example.todoapp.utilities.GetResources
import com.example.todoapp.utilities.Repository
import com.example.todoapp.utilities.SubtasksAdapter
import com.example.todoapp.utilities.TaskSerializer
import com.example.todoapp.viewmodels.SubtasksViewModel
import com.example.todoapp.viewmodels.SubtasksViewModelFactory
import com.example.todoapp.viewmodels.TasksViewModel
import com.example.todoapp.viewmodels.TasksViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class EditTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTaskBinding
    private lateinit var subtasksViewModel: SubtasksViewModel
    private lateinit var viewModel: TasksViewModel
    private lateinit var task: TaskSerializer
    private lateinit var res: GetResources

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)

        val db = DB.getInstance(this@EditTaskActivity)
        val repository = Repository(db)
        val subFactory = SubtasksViewModelFactory(application, repository)
        val factory = TasksViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, factory)[TasksViewModel::class.java]
        subtasksViewModel = ViewModelProvider(this, subFactory)[SubtasksViewModel::class.java]
        setContentView(binding.root)
        res = GetResources(applicationContext)
        binding.apply {
            task = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("Task", TaskSerializer::class.java)!!
            } else {
                @Suppress("DEPRECATION")
                intent.getSerializableExtra("Task") as TaskSerializer
            }
            val adapter =
                SubtasksAdapter(
                    subtasksViewModel.getAll(task.id as Int).value?.reversed()
                        ?: listOf(), subtasksViewModel
                )
            val layoutManager = LinearLayoutManager(this@EditTaskActivity)
            val observer = Observer<List<Subtask>> {
                adapter.subtasks = it.reversed()
                adapter.notifyDataSetChanged()
            }
            subtasksViewModel.getAll(task.id as Int).observe(this@EditTaskActivity, observer)
            rvSubtasks.adapter = adapter
            rvSubtasks.layoutManager = layoutManager

            etTitleEdit.setText(task.title)
            etDescriptionEdit.setText(task.description)
            tvDate.text = task.date

            btnTaskCompleted.text = res.getCompletedButtonText(task.isCompleted)
            btnTaskCompleted.setBackgroundColor(res.getCompletedButtonColor(task.isCompleted))

            btnImportant.setImageResource(res.getImportantButtonIcon(task.isImportant))
            btnImportant.setOnClickListener {
                task.isImportant = !task.isImportant
                val btnImportant = it as ImageButton
                btnImportant.setImageResource(res.getImportantButtonIcon(task.isImportant))
                viewModel.update(TaskSerializer.toTaskEntity(task))
            }
            btnTaskCompleted.setOnClickListener {
                task.isCompleted = !task.isCompleted
                val btnCompleted = it as Button
                btnTaskCompleted.setBackgroundColor(res.getCompletedButtonColor(task.isCompleted))
                btnCompleted.text = res.getCompletedButtonText(task.isCompleted)
                viewModel.update(TaskSerializer.toTaskEntity(task))
            }
            btnDone.setOnClickListener {
                task.title = etTitleEdit.text.toString()
                task.description = etDescriptionEdit.text.toString()
                val intent = Intent().putExtra("Task", task)
                setResult(7355608, intent)
                finish()
            }
            btnAddSubtask.setOnClickListener {
                subtasksViewModel.add(Subtask("", taskID = task.id!!))
            }

            tvDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    this@EditTaskActivity,
                    { _, yearPicked, monthPicked, dayPicked ->
                        calendar.set(Calendar.YEAR, yearPicked)
                        calendar.set(Calendar.MONTH, monthPicked)
                        calendar.set(Calendar.DAY_OF_MONTH, dayPicked)
                        val formatted =
                            SimpleDateFormat("dd MMMM yyyy", Locale.US).format(calendar.time)
                        tvDate.text = formatted
                        task.date = formatted
                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.show()
            }
            btnBack.setOnClickListener { finish() }
        }
    }

}