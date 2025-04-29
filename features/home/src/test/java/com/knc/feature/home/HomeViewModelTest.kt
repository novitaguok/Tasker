package com.knc.feature.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.knc.core.domain.DeleteTaskUseCase
import com.knc.core.domain.GetAllTaskUseCase
import com.knc.core.domain.UpdateTaskStatusUseCase
import com.knc.core.model.Task
import com.knc.core.model.TaskStatus
import com.knc.feature.home.ui.HomeUiState
import com.knc.feature.home.ui.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    // Executes each task synchronously using Architecture Components
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Test dispatcher for coroutines
    private val testDispatcher = TestCoroutineDispatcher()

    // Mock dependencies
    @MockK
    private lateinit var getAllTaskUseCase: GetAllTaskUseCase

    @MockK
    private lateinit var deleteTaskUseCase: DeleteTaskUseCase

    @MockK
    private lateinit var updateTaskStatusUseCase: UpdateTaskStatusUseCase

    // The class under test
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        // Initialize mockk
        MockKAnnotations.init(this)

        // Set the main dispatcher for coroutines
        Dispatchers.setMain(testDispatcher)

        // Setup default behavior for mocks
        coEvery { getAllTaskUseCase.invoke() } returns flowOf(emptyList())
        coEvery { deleteTaskUseCase.invoke(any()) } just runs
        coEvery { updateTaskStatusUseCase.invoke(any()) } just runs

        // Initialize the view model with mocked dependencies
        homeViewModel = HomeViewModel(
            getAllTaskUseCase,
            deleteTaskUseCase,
            updateTaskStatusUseCase,
            testDispatcher
        )
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher
        Dispatchers.resetMain()

        // Clean up the test dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getAllTask should update uiState with Success containing tasks`() =
        testDispatcher.runBlockingTest {
            // Given
            val tasks = listOf(
                Task(
                    id = 1,
                    title = "Task 1",
                    description = "Description 1",
                    status = TaskStatus.PENDING,
                    timestamp = Clock.System.now()
                ),
                Task(
                    id = 2,
                    title = "Task 2",
                    description = "Description 2",
                    status = TaskStatus.COMPLETE,
                    timestamp = Clock.System.now()
                )
            )
            coEvery { getAllTaskUseCase.invoke() } returns flowOf(tasks)

            // When
            homeViewModel.getAllTask()

            // Then
            val currentState = homeViewModel.uiState.value
            assert(currentState is HomeUiState.Success)
            assertEquals((currentState as HomeUiState.Success).data, tasks)
        }

    @Test
    fun `deleteTask should call deleteTaskUseCase with correct task`() =
        testDispatcher.runBlockingTest {
            // Given
            val task = Task(
                id = 1,
                title = "Task 1",
                description = "Description 1",
                status = TaskStatus.PENDING,
                timestamp = Clock.System.now()
            )

            // When
            homeViewModel.deleteTask(task)

            // Then
            coVerify { deleteTaskUseCase.invoke(task) }
        }

    @Test
    fun `markComplete should call updateTaskStatusUseCase with task status set to COMPLETE`() =
        testDispatcher.runBlockingTest {
            // Given
            val task = Task(
                id = 1,
                title = "Task 1",
                description = "Description 1",
                status = TaskStatus.PENDING,
                timestamp = Clock.System.now()
            )
            val completedTask = task.copy(status = TaskStatus.COMPLETE)

            // When
            homeViewModel.markComplete(task)

            // Then
            coVerify { updateTaskStatusUseCase.invoke(match { it.status == TaskStatus.COMPLETE && it.id == task.id }) }
        }

    @Test
    fun `init should call getAllTask`() = testDispatcher.runBlockingTest {
        // Given - ViewModel is initialized in setup()

        // Then - verify that getAllTaskUseCase was called during initialization
        coVerify { getAllTaskUseCase.invoke() }
    }
}