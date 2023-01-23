package com.example.todoapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.utilities.CreateTaskDialog
import com.example.todoapp.viewmodels.TaskViewModel

//import com.example.todoapp.models


class MainActivity : AppCompatActivity(), CreateTaskDialog.CreateTaskDialogInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TaskViewModel
    private lateinit var tasksFragment: TasksFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

    override fun addTask(title: String, task: String, date: String) {
        val dialog = CreateTaskDialog()
        dialog.show(supportFragmentManager, "Add task")
        //TODO: not implemented yet
    }

    private fun showPopup(v: View) {
        //TODO: not implemented yet
    }
}