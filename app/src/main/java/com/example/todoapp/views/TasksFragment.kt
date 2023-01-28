package com.example.todoapp.views

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.TasksFragmentBinding
import com.example.todoapp.models.tasks.Task
import com.example.todoapp.models.tasks.database.DB
import com.example.todoapp.utilities.Repository
import com.example.todoapp.utilities.SwipeGesture
import com.example.todoapp.utilities.TasksAdapter
import com.example.todoapp.viewmodels.TasksViewModel
import com.example.todoapp.viewmodels.TasksViewModelFactory
import com.google.android.material.divider.MaterialDividerItemDecoration


class TasksFragment : Fragment() {
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var binding: TasksFragmentBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var viewModel: TasksViewModel
    private lateinit var activityContext: Context
    private lateinit var application: Application

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
        application = (context as Activity).application
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        layoutManager = LinearLayoutManager(activityContext)
        binding = TasksFragmentBinding.inflate(inflater, container, false)
        val database = DB.getInstance(activityContext)
        val repository = Repository(database)
        val viewModelFactory = TasksViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[TasksViewModel::class.java]

        tasksAdapter = TasksAdapter(viewModel.getAll().value?.reversed() ?: listOf(), viewModel)

        val swipeGesture = SwipeGesture(viewModel, tasksAdapter, activityContext)
        swipeGesture.attachToRecyclerView(binding.rvTasks)

        val observer = Observer<List<Task>> {
            tasksAdapter.tasks = it.reversed()
            tasksAdapter.notifyDataSetChanged()
        }
        viewModel.getAll().observe(viewLifecycleOwner, observer)

        binding.apply {
            rvTasks.adapter = tasksAdapter
            rvTasks.layoutManager = layoutManager
            rvTasks.addItemDecoration(
                MaterialDividerItemDecoration(
                    activityContext,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        return binding.root
    }

}