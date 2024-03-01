package com.avcristina.cleancodemultimoduleapp.domain.usecase

import com.avcristina.cleancodemultimoduleapp.domain.models.UserName
import com.avcristina.cleancodemultimoduleapp.domain.repository.UserRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetUserNameUseCaseTest {

    // Замокали репозиторий
    val userRepository = mock<UserRepository>()

    // Другой способ замокать, но 1ый предпочтительнее, т к там val
    @Mock
    lateinit var userRepository2: UserRepository

    // Этим тестом мы проверяем не исказились ли данные внутри use сase-а
    @Test
    fun repositoryShouldReturnCorrectData() {

        val testUserName = UserName(firstName = "test first name", lastName = "test last name")
        // Как только будет вызван userRepository.getName(), мы должны вернуть значение testUserName
        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)

        val useCase = GetUserNameUseCase(userRepository)
        val actual = useCase.execute()
        val expected = UserName(firstName = "test first name", lastName = "test last name")

        // Сравнение ожидаемого результата с действительным
        assertEquals(expected, actual)
    }
}