package com.example.todoapp.views

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.utilities.TasksAdapter
import com.example.todoapp.databinding.TasksFragmentBinding
import com.example.todoapp.viewmodels.TaskViewModel

class TasksFragment : Fragment() {
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var binding: TasksFragmentBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var viewModel: TaskViewModel
    private lateinit var activityContext: Context
    private lateinit var application: Application

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
        application = (context as Activity).application
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO: not implemented
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}