package com.avcristina.cleancodemultimoduleapp.domain.usecase

import com.avcristina.cleancodemultimoduleapp.domain.models.SaveUserNameParam

class SaveUserNameUseCase {

    fun execute(param: SaveUserNameParam): Boolean {
        return param.name.isNotEmpty()
    }
}