package com.knc.core.data.model

import com.knc.core.database.model.TaskEntity
import com.knc.core.model.Task
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun Task.asEntity() = TaskEntity(
    id = id ?: 0,
    title = title,
    desc = description,
    status = status
)
