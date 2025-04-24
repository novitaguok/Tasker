package com.knc.feature.create.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knc.core.domain.CreateTaskUseCase
import com.knc.core.model.Task
import com.knc.core.model.TaskStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@FlowPreview
@ExperimentalTime
class CreateTaskViewModel(
    private val createTaskUseCase: CreateTaskUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow<CreateTaskUiState>(CreateTaskUiState.Loading)
    val uiState: StateFlow<CreateTaskUiState> = _uiState

    private val _titleInputState = MutableStateFlow("")
    val titleInputState = _titleInputState.asStateFlow()

    private val _descInputState = MutableStateFlow("")
    val descInputState = _descInputState.asStateFlow()

    fun updateTitleTextInput(text: String) {
        _titleInputState.value = text
    }

    fun updateDescTextInput(text: String) {
        _descInputState.value = text
    }

    fun createTask() {
        viewModelScope.launch(ioDispatcher) {
            val task = Task(
                title = _titleInputState.value.trim(),
                description = _descInputState.value.trim(),
                status = TaskStatus.PENDING,
                timestamp = Clock.System.now()
            )
            createTaskUseCase.invoke(task)
        }
    }
}