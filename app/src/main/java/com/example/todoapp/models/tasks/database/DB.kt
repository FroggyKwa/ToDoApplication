package com.example.todoapp.models.tasks.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.models.tasks.Task


@Database(
    entities = [Task::class],
    exportSchema = true,
    version = 1
)
abstract class DB : RoomDatabase() {
    abstract fun getTasksDao(): TasksDao

    companion object {
        @Volatile
        private lateinit var instance: DB

        fun getInstance(context: Context): DB {
            synchronized(this) {
                if (!::instance.isInitialized) {
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
            ).build()
        }
    }
}