package com.knc.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.knc.core.database.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE id = :id")
    fun getTaskById(id: Int): Flow<TaskEntity>

    @Insert
    fun insertTask(task: TaskEntity)

    @Update
    fun updateTaskStatus(task: TaskEntity)

    @Delete
    fun deleteTask(task: TaskEntity)
}