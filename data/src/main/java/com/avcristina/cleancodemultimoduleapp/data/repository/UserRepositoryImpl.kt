package com.avcristina.cleancodemultimoduleapp.data.repository

import com.avcristina.cleancodemultimoduleapp.data.storage.UserStorage
import com.avcristina.cleancodemultimoduleapp.data.storage.models.User
import com.avcristina.cleancodemultimoduleapp.domain.models.SaveUserNameParam
import com.avcristina.cleancodemultimoduleapp.domain.models.UserName
import com.avcristina.cleancodemultimoduleapp.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userStorage: UserStorage
    // если в дальнейшем потребуется добавить сохранение данных на сервер,
    // то в конструктор передадим еще и
    // private val networkApi: NetworkApi
) : UserRepository {

    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        val user = mapToStorage(saveParam)
        return userStorage.save(user)
    }

    override fun getName(): UserName {
        val user = userStorage.get()
        return mapToDomain(user)
    }

    // а ниже, если понадобятся, добавим методы, вызывающие методы save и get networkApi


    // мапперы можно реализовать так, а можно как extention
    private fun mapToStorage(saveParam: SaveUserNameParam): User {
        return User(firstName = saveParam.name, lastName = "")
    }

    private fun mapToDomain(user: User): UserName {
        return UserName(firstName = user.firstName, lastName = user.lastName)
    }
}