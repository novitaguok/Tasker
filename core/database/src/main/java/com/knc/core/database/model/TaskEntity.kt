package com.knc.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.knc.core.model.Task
import com.knc.core.model.TaskStatus
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val desc: String,

    @ColumnInfo(name = "status")
    val status: TaskStatus,
)

@ExperimentalTime
fun TaskEntity.asExternalModel() = Task(
    id = id,
    title = title,
    description = desc,
    status = status,
    timestamp = Clock.System.now()
)