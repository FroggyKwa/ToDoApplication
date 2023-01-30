package com.example.todoapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.models.database.DB
import com.example.todoapp.models.database.tasks.Task
import com.example.todoapp.utilities.Consts.TAB_NAMES
import com.example.todoapp.utilities.CreateTaskDialog
import com.example.todoapp.utilities.Repository
import com.example.todoapp.utilities.PagerAdapter
import com.example.todoapp.viewmodels.TasksViewModel
import com.example.todoapp.viewmodels.TasksViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), CreateTaskDialog.CreateTaskDialogInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TasksViewModel
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = DB.getInstance(this@MainActivity)
        val repository = Repository(db)
        val factory = TasksViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, factory)[TasksViewModel::class.java]

        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        val adapter = PagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) {
                tab, position ->
            tab.text = TAB_NAMES[position]
        }.attach()
        binding.apply {
            fbtnAdd.setOnClickListener {
                CreateTaskDialog().show(supportFragmentManager, "Add task")
            }
            moreOptions.setOnClickListener {

            }
        }
    }

    override fun addTask(title: String, description: String, date: String) {
        val task = Task(title, description, date)
        viewModel.add(task)
    }
}