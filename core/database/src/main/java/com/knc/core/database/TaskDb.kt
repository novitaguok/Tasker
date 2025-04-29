package com.knc.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.knc.core.database.dao.TaskDao
import com.knc.core.database.model.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = true
)
abstract class TaskDb : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}