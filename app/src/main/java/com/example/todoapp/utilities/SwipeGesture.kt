package com.example.todoapp.utilities

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.todoapp.viewmodels.TasksViewModel

class SwipeGesture(
    viewModel: TasksViewModel,
    adapter: TasksAdapter,
    context: Context
)
    : ItemTouchHelper(SwipeGestureCallback(viewModel, adapter, context))
{

}