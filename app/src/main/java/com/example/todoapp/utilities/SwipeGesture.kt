package com.example.todoapp.utilities

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.todoapp.adapters.TasksAdapter
import com.example.todoapp.viewmodels.TasksViewModel

class SwipeGesture(
    viewModel: TasksViewModel,
    adapter: TasksAdapter,
    context: Context,
    view: View
)
    : ItemTouchHelper(SwipeGestureCallback(viewModel, adapter, context, view))
{

}