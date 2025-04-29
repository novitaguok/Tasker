package com.knc.core.data.repository

import com.knc.core.model.Task
import kotlinx.coroutines.flow.Flow
import kotlin.time.ExperimentalTime

@ExperimentalTime
interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    fun getTaskById(id: Int): Flow<Task>
    fun insertTask(task: Task)
    fun updateTaskStatus(task: Task)
    fun deleteTask(task: Task)
}