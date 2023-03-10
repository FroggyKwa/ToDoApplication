package com.example.todoapp.views

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.TasksFragmentBinding
import com.example.todoapp.models.database.DB
import com.example.todoapp.models.database.tasks.Task
import com.example.todoapp.utilities.Repository
import com.example.todoapp.utilities.SwipeGesture
import com.example.todoapp.utilities.TaskSerializer
import com.example.todoapp.adapters.TasksAdapter
import com.example.todoapp.utilities.TaskUtils.openEditTaskActivity
import com.example.todoapp.utilities.TaskUtils.showPopup
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
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

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

        tasksAdapter = TasksAdapter(
            viewModel.getAll().value?.reversed() ?: listOf(),
            viewModel
        ) {
            openEditTaskActivity(it, requireContext(), resultLauncher)
        }
        val swipeGesture = SwipeGesture(viewModel, tasksAdapter, activityContext, binding.root)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.findViewById<ImageButton>(R.id.moreOptions)?.setOnClickListener {
            showPopup(tasksAdapter, it, activityContext)
        }
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == 7355608) {
                    val data: Intent = result.data ?: return@registerForActivityResult
                    val task =
                        TaskSerializer.toTaskEntity(data.getSerializableExtra("Task") as TaskSerializer)
                    viewModel.update(task)
                }

            }

    }

}