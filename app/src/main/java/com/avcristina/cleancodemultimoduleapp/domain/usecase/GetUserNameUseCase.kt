package com.avcristina.cleancodemultimoduleapp.domain.usecase

import com.avcristina.cleancodemultimoduleapp.domain.models.UserName

class GetUserNameUseCase {

    fun execute(): UserName {
        return UserName(firstName = "John", lastName = "Doe")
    }
}