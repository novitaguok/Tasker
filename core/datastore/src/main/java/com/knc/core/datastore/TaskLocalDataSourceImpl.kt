package com.knc.core.datastore

import com.knc.core.database.dao.TaskDao
import com.knc.core.database.model.TaskEntity
import kotlinx.coroutines.flow.Flow


class TaskLocalDataSourceImpl(private val taskDao: TaskDao) : TaskLocalDataSource {
    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskDao.getAllTasks()
    }

    override fun getTaskById(id: Int): Flow<TaskEntity> {
        return taskDao.getTaskById(id)
    }

    override fun insertTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }

    override fun updateTaskStatus(task: TaskEntity) {
        taskDao.updateTaskStatus(task)
    }

    override fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }
}