package com.singlepointsolution.common

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.singlepointsolution.common.repository.TodoRepository
import com.singlepointsolution.common.sharedviewmodel.TodoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class TodoViewModelTest {

    @Mock
    private lateinit var repository: TodoRepository

    private lateinit var viewmodel: TodoViewModel

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    private val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        viewmodel = com.singlepointsolution.common.sharedviewmodel.TodoViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test get all task with empty search test`() {
        val taskState = viewmodel.allTasks.value
        viewmodel.searchTextState.value = ""
        viewmodel.getAllTasks()
        Assert.assertEquals(com.singlepointsolution.common.utils.RequestState.Loading, taskState)
    }

    @Test
    fun `test get all task with task search test`() {
        val taskState = viewmodel.allTasks.value
        viewmodel.searchTextState.value = "task"
        viewmodel.getAllTasks()
        Assert.assertEquals(com.singlepointsolution.common.utils.RequestState.Loading, taskState)
    }

    @Test
    fun `test validation error`() {
        viewmodel.note.value = ""
        val taskState = viewmodel.taskState.value
        viewmodel.addTask()
        Assert.assertEquals(com.singlepointsolution.common.utils.TaskState.IDLE, taskState)
    }

    @Test
    fun `test error case`() {
        viewmodel.note.value = "error"
        val taskState = viewmodel.taskState.value
        viewmodel.addTask()
        Assert.assertEquals(com.singlepointsolution.common.utils.TaskState.IDLE, taskState)
    }

    @Test
    fun `test update note`() {
        viewmodel.updateNote("task")
        Assert.assertEquals("task", viewmodel.note.value )
    }
}