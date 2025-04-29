package com.knc.feature.home.ui

import com.knc.core.model.Task
import kotlin.time.ExperimentalTime

@ExperimentalTime
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val data: List<Task>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}