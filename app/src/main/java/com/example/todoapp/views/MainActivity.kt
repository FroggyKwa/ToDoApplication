package com.example.todoapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.models.database.DB
import com.example.todoapp.models.database.tasks.Task
import com.example.todoapp.utilities.CreateTaskDialog
import com.example.todoapp.utilities.Repository
import com.example.todoapp.viewmodels.TasksViewModel
import com.example.todoapp.viewmodels.TasksViewModelFactory

class MainActivity : AppCompatActivity(), CreateTaskDialog.CreateTaskDialogInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TasksViewModel
    private lateinit var tasksFragment: TasksFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = DB.getInstance(this@MainActivity)
        val repository = Repository(db)
        val factory = TasksViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, factory)[TasksViewModel::class.java]

        tasksFragment = TasksFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frgTasks, tasksFragment)
            commit()
        }

        binding.apply {
            fbtnAdd.setOnClickListener {
                CreateTaskDialog().show(supportFragmentManager, "Add task")
            }
        }
    }

    override fun addTask(title: String, description: String, date: String) {
        val task = Task(title, description, date)
        viewModel.add(task)
    }
}