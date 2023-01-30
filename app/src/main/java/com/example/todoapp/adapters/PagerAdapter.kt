package com.example.todoapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todoapp.views.CompletedTasksFragment
import com.example.todoapp.views.ImportantTasksFragment
import com.example.todoapp.views.TasksFragment


class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragments = listOf(TasksFragment(), ImportantTasksFragment(), CompletedTasksFragment())
    override fun getItemCount(): Int {
        return 3
    }
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
        }
    }