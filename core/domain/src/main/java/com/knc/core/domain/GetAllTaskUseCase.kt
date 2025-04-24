package com.knc.core.domain

import com.knc.core.data.repository.TaskRepository
import com.knc.core.model.Task
import kotlinx.coroutines.flow.Flow
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GetAllTaskUseCase(private val taskRepository: TaskRepository) {
    operator fun invoke() : Flow<List<Task>> {
        return taskRepository.getAllTasks()
    }
}