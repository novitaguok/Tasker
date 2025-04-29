package com.knc.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knc.core.domain.DeleteTaskUseCase
import com.knc.core.domain.GetAllTaskUseCase
import com.knc.core.domain.UpdateTaskStatusUseCase
import com.knc.core.model.Task
import com.knc.core.model.TaskStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime

@ExperimentalTime
class HomeViewModel(
    private val getAllTaskUseCase: GetAllTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskStatusUseCase: UpdateTaskStatusUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        getAllTask()
    }

    fun getAllTask() {
        viewModelScope.launch {
            getAllTaskUseCase.invoke().collectLatest {
                _uiState.value = HomeUiState.Success(it)
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(ioDispatcher) {
            deleteTaskUseCase.invoke(task)
        }
    }

    fun markComplete(task: Task) {
        viewModelScope.launch(ioDispatcher) {
            updateTaskStatusUseCase.invoke(task.copy(status = TaskStatus.COMPLETE))
        }
    }
}
