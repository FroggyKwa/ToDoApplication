package com.example.todoapp.models.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.models.database.subtasks.Subtask
import com.example.todoapp.models.database.subtasks.SubtaskDao
import com.example.todoapp.models.database.tasks.Task
import com.example.todoapp.models.database.tasks.TasksDao


@Database(
    entities = [Task::class, Subtask::class],
    exportSchema = true,
    version = 1,
)
abstract class DB : RoomDatabase() {
    abstract fun getTasksDao(): TasksDao
    abstract fun getSubtasksDao(): SubtaskDao

    companion object {
        @Volatile
        private lateinit var instance: DB

        fun getInstance(context: Context): DB {
            synchronized(this) {
                if (!Companion::instance.isInitialized) {
                    instance = createDatabase(context)
                }
                return instance
            }
        }
        private fun createDatabase(context: Context): DB {
            return Room.databaseBuilder(
                context,
                DB::class.java,
                "TasksDatabase"
            ).fallbackToDestructiveMigration().build()
        }
    }
}