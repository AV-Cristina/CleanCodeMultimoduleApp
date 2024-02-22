package com.avcristina.cleancodemultimoduleapp.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avcristina.cleancodemultimoduleapp.data.repository.UserRepositoryImpl
import com.avcristina.cleancodemultimoduleapp.data.storage.SharedPrefUserStorage
import com.avcristina.cleancodemultimoduleapp.domain.usecase.GetUserNameUseCase
import com.avcristina.cleancodemultimoduleapp.domain.usecase.SaveUserNameUseCase

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    // далее для создания этих объектов будет использоваться DI
    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = context))
    }

    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserNameUseCase(userRepository)
    }

    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserNameUseCase(userRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        ) as T
    }
}