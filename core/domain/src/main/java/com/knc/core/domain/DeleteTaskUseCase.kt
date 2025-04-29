package com.knc.core.domain

import com.knc.core.data.repository.TaskRepository
import com.knc.core.model.Task
import kotlin.time.ExperimentalTime

@ExperimentalTime
class DeleteTaskUseCase(private val taskRepository: TaskRepository) {
    operator fun invoke(task: Task) {
        return taskRepository.deleteTask(task)
    }
}
