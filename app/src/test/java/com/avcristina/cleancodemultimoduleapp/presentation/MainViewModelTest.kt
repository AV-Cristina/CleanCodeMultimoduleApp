package com.avcristina.cleancodemultimoduleapp.presentation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.avcristina.cleancodemultimoduleapp.domain.models.SaveUserNameParam
import com.avcristina.cleancodemultimoduleapp.domain.usecase.GetUserNameUseCase
import com.avcristina.cleancodemultimoduleapp.domain.usecase.SaveUserNameUseCase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class MainViewModeTest {

    val getUserNameUseCase = mock<GetUserNameUseCase>()
    val saveUserNameUseCase = mock<SaveUserNameUseCase>()

    // Вариант 1
    lateinit var viewModel: MainViewModel

    // Выполняется до каждого теста
    @Before
    fun setUp() {
        // Вариант 1: Создавать VM перед каждым тестом
        viewModel = MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )

        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    // Выполняется после каждого теста
    @After
    fun tearDown() {
        Mockito.reset(getUserNameUseCase)
        Mockito.reset(saveUserNameUseCase)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    @Test
    fun `should save user name and return true`() {
        val saveResult = true
        val testSaveText = "Test user name"
        val testParams = SaveUserNameParam(name = testSaveText)

        // Настроили, что будет возвращать моковый UseCase
        Mockito.`when`(saveUserNameUseCase.execute(param = testParams)).thenReturn(saveResult)

        // Вариант 2: Создавать VM в каждом тесте
//        val viewModel = MainViewModel(
//            getUserNameUseCase = getUserNameUseCase,
//            saveUserNameUseCase = saveUserNameUseCase
//        )

        // Сохранили
        viewModel.save(text = testSaveText)

        // Проверяем сохранилось ли
        val expected = "Save result = true"
        val actual = viewModel.resultLiveData.value
        assertEquals(expected, actual)

        // Проверяем, что метод был вызван с указанными параметрами 1 раз
        Mockito.verify(saveUserNameUseCase, times(1)).execute(param = testParams)
    }

    @Test
    fun `should save user name and return false`() {
        val saveResult = false
        val testSaveText = "Test user name"
        val testParams = SaveUserNameParam(name = testSaveText)

        Mockito.`when`(saveUserNameUseCase.execute(param = testParams)).thenReturn(saveResult)

        viewModel.save(text = testSaveText)

        val expected = "Save result = false"
        val actual = viewModel.resultLiveData.value
        assertEquals(expected, actual)

        Mockito.verify(saveUserNameUseCase, times(1)).execute(param = testParams)
    }

    @Test
    fun `should load user name`() {
    }
}