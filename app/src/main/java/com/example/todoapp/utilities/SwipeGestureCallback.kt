package com.example.todoapp.utilities

import android.content.Context
import android.graphics.Canvas
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.room.PrimaryKey
import com.example.todoapp.R
import com.example.todoapp.viewmodels.TasksViewModel
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class SwipeGestureCallback(
    private val viewModel: TasksViewModel,
    private val adapter: TasksAdapter,
    private val context: Context,
    private val view: View
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val task = adapter.tasks[viewHolder.bindingAdapterPosition]
        viewModel.delete(task)
        Snackbar.make(view, R.string.task_deleted, Snackbar.LENGTH_LONG)
            .setAction(R.string.undo) {
                viewModel.add(task)
            }
            .show()
        adapter.notifyItemRemoved(viewHolder.bindingAdapterPosition)

    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
            .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.delete_color))
            .addSwipeLeftActionIcon(R.drawable.ic_outline_delete_24)
            .create()
            .decorate()
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

}