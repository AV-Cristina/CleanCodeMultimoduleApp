package com.avcristina.cleancodemultimoduleapp.data.storage

import com.avcristina.cleancodemultimoduleapp.data.storage.models.User

interface UserStorage {
    fun save(user: User): Boolean

    fun get(): User
}