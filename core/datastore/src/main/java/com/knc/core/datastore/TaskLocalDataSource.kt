package com.knc.core.datastore

import com.knc.core.database.model.TaskEntity
import kotlinx.coroutines.flow.Flow


interface TaskLocalDataSource {
    fun getAllTasks(): Flow<List<TaskEntity>>
    fun getTaskById(id: Int): Flow<TaskEntity>
    fun insertTask(task: TaskEntity)
    fun updateTaskStatus(task: TaskEntity)
    fun deleteTask(task: TaskEntity)
}