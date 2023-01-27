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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.TasksFragmentBinding
import com.example.todoapp.utilities.SwipeGesture
import com.example.todoapp.utilities.TasksAdapter
import com.example.todoapp.viewmodels.TasksViewModel
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
        viewModel = ViewModelProvider(this)[TasksViewModel::class.java]
        tasksAdapter = TasksAdapter(viewModel.getAll())

        val swipeGesture = SwipeGesture(viewModel, tasksAdapter, activityContext)
        swipeGesture.attachToRecyclerView(binding.rvTasks)

        binding.apply {
            rvTasks.adapter = tasksAdapter
            rvTasks.layoutManager = layoutManager
            rvTasks.addItemDecoration(MaterialDividerItemDecoration(
                activityContext,
                LinearLayoutManager.VERTICAL
            ))
        }
        return binding.root
    }

}