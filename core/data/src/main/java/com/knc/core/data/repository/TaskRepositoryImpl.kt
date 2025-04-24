package com.knc.core.data.repository

import com.knc.core.data.model.asEntity
import com.knc.core.database.model.TaskEntity
import com.knc.core.database.model.asExternalModel
import com.knc.core.datastore.TaskLocalDataSource
import com.knc.core.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.time.ExperimentalTime

@ExperimentalTime
class TaskRepositoryImpl(
    private val taskLocalDataSource: TaskLocalDataSource
) : TaskRepository {

    override fun getAllTasks(): Flow<List<Task>> {
        return taskLocalDataSource.getAllTasks().map { it.map(TaskEntity::asExternalModel) }
    }

    override fun getTaskById(id: Int): Flow<Task> {
        return taskLocalDataSource.getTaskById(id).map { it.asExternalModel() }
    }

    override fun insertTask(task: Task) {
        taskLocalDataSource.insertTask(task.asEntity())
    }

    override fun updateTaskStatus(task: Task) {
        taskLocalDataSource.updateTaskStatus(task.asEntity())
    }

    override fun deleteTask(task: Task) {
        taskLocalDataSource.deleteTask(task.asEntity())
    }
}