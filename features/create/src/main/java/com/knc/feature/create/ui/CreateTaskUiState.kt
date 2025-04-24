package com.knc.feature.create.ui

import com.knc.core.model.Task
import kotlin.time.ExperimentalTime

@ExperimentalTime
sealed interface CreateTaskUiState {
    data object Loading : CreateTaskUiState
    data class Success(val data: Task) : CreateTaskUiState
    data class Error(val message: String) : CreateTaskUiState
}