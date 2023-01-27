package com.example.todoapp.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.models.Task
import com.example.todoapp.utilities.CreateTaskDialog
import com.example.todoapp.viewmodels.TaskViewModel

class MainActivity : AppCompatActivity(), CreateTaskDialog.CreateTaskDialogInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TaskViewModel
    private lateinit var tasksFragment: TasksFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        tasksFragment = TasksFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frgTasks, tasksFragment)
            commit()
        }

        binding.apply {
            fbtnAdd.setOnClickListener {
                CreateTaskDialog().show(supportFragmentManager, "Add task")
            }
            burgerMenu.setOnClickListener { showPopup(burgerMenu) }
        }
    }

    override fun addTask(title: String, description: String, date: String) {
        val task = Task(title, description, date)
        viewModel.add(task)
        //TODO: not implemented yet
    }

    private fun showPopup(view: View) {
        //TODO: not implemented yet
    }
}