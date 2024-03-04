package com.avcristina.cleancodemultimoduleapp.domain.usecase

import com.avcristina.cleancodemultimoduleapp.domain.models.SaveUserNameParam
import com.avcristina.cleancodemultimoduleapp.domain.models.UserName
import com.avcristina.cleancodemultimoduleapp.domain.repository.UserRepository
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class SaveUserNameUseCaseTest {

    val userRepository = mock<UserRepository>()

    @After // @AfterEach for JUnit 5
    fun tearDown() {
        Mockito.reset(userRepository)
    }

    @Test
    fun shouldNotSaveDataIfNameWasAlreadySaved() {
        // Прописываем поведение при вызове метода userRepository.getName(), так
        // чтобы он возвращал созданное нами testUserName, тем самым
        // мы эмулируем ситуацию, что "test first name" уже сохранено
        val testUserName = UserName(firstName = "test first name", lastName = "test last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)

        val useCase = SaveUserNameUseCase(userRepository = userRepository)
        // Создаем тестовый testParam - имя, которое будем пытаться сохранить,
        // оно должно совпадать с ранее якобы сохраненным именем "test first name"
        val testParams = SaveUserNameParam(name = "test first name")

        // Пытаемся сохранить имя, которое уже якобы сохранено
        val actual = useCase.execute(testParams)
        // То, что должен вернуть useCase.execute для сохраненного имени
        val expected = true
        // Проверяем, что у useCase вернул то, что мы ожидали в случае, когда имя уже сохранено
        // т. е. то, что вернется true
        assertEquals(expected, actual)
        // TODO: эту проверку лучше вынести в отдельный тест для того,
        // чтобы в случае его падения, четко понимать какая именно проверка не прошла.
        // Но и так тоже делают.
        Mockito.verify(userRepository, never()).saveName(saveParam = any())
    }

    @Test
    fun shouldReturnTrueIfSaveWasSuccessful() {
        val testUserName = UserName(firstName = "test first name", lastName = "test last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)

        val expected = true
        val testParam = SaveUserNameParam(name = "new first name")
        Mockito.`when`(userRepository.saveName(saveParam = testParam)).thenReturn(expected)

        val useCase = SaveUserNameUseCase(userRepository = userRepository)
        val actual = useCase.execute(testParam)

        assertEquals(expected, actual)
        // TODO: эту проверку лучше вынести в отдельный тест
        Mockito.verify(userRepository, times(1)).saveName(saveParam = testParam)
    }

    @Test
    fun shouldReturnFalseIfSaveWasSuccessful() {
        val testUserName = UserName(firstName = "test first name", lastName = "test last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)

        val expected = false
        val testParam = SaveUserNameParam(name = "new first name")
        Mockito.`when`(userRepository.saveName(saveParam = testParam)).thenReturn(expected)

        val useCase = SaveUserNameUseCase(userRepository = userRepository)
        val actual = useCase.execute(testParam)

        assertEquals(expected, actual)
        // TODO: эту проверку лучше вынести в отдельный тест
        Mockito.verify(userRepository, times(1)).saveName(saveParam = testParam)
    }
}