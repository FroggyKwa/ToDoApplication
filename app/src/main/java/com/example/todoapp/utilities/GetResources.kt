package com.example.todoapp.utilities

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.todoapp.R

class GetResources(val context: Context) {
    fun getCompletedButtonText(isCompleted: Boolean): String {
        return if (!isCompleted) context.resources.getString(R.string.mark_completed)
        else context.resources.getString(R.string.mark_incompleted)
    }

    fun getCompletedButtonColor(isCompleted: Boolean): Int {
            return if (!isCompleted) ContextCompat.getColor(context, R.color.primary)
            else ContextCompat.getColor(context, R.color.delete_color)
        }

    fun getImportantButtonIcon(isImportant: Boolean): Int {
            return if (!isImportant) R.drawable.ic_baseline_star_border_24
            else R.drawable.ic_baseline_star_24
    }
}