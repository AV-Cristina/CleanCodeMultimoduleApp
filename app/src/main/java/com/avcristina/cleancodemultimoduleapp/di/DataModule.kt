package com.avcristina.cleancodemultimoduleapp.di

import android.content.Context
import com.avcristina.cleancodemultimoduleapp.data.repository.UserRepositoryImpl
import com.avcristina.cleancodemultimoduleapp.data.storage.SharedPrefUserStorage
import com.avcristina.cleancodemultimoduleapp.data.storage.UserStorage
import com.avcristina.cleancodemultimoduleapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideUserStorage(@ApplicationContext context: Context): UserStorage {
        return SharedPrefUserStorage(context = context)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userStorage: UserStorage): UserRepository {
        return UserRepositoryImpl(userStorage = userStorage)
    }
}