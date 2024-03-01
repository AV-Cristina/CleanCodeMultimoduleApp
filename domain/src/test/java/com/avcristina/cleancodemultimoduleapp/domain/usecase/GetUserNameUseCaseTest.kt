package com.avcristina.cleancodemultimoduleapp.domain.usecase

import com.avcristina.cleancodemultimoduleapp.domain.models.SaveUserNameParam
import com.avcristina.cleancodemultimoduleapp.domain.models.UserName
import com.avcristina.cleancodemultimoduleapp.domain.repository.UserRepository
import org.junit.Assert.assertEquals
import org.junit.Test

// Создали фейковый репозиторий, чтобы он нам вернул тестовые данные
// Обычно вместо фейковый экземпляров используют моки, их рассмотрим далее
class TestRepository: UserRepository {
    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        TODO("Not yet implemented")
    }

    override fun getName(): UserName {
        return UserName(firstName = "test first name", lastName = "test last name")
    }
}

class GetUserNameUseCaseTest {

    @Test
    fun simpleTestExample() {
        val expected = 4
        val actual = 2 + 2
        assertEquals(expected, actual)
    }

    // Этим тестом мы проверяем не исказились ли данные внутри use сase-а
    @Test
    fun repositoryShouldReturnCorrectData() {
        val testRepository = TestRepository()
        val useCase = GetUserNameUseCase(testRepository)
        val actual = useCase.execute()
        val expected = UserName(firstName = "test first name", lastName = "test last name")
        // Сравнение ожидаемого результата с действительным
        assertEquals(expected, actual)
    }
}