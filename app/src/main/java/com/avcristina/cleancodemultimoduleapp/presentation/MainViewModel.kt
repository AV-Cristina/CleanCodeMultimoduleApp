package com.avcristina.cleancodemultimoduleapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avcristina.cleancodemultimoduleapp.domain.models.SaveUserNameParam
import com.avcristina.cleancodemultimoduleapp.domain.models.UserName
import com.avcristina.cleancodemultimoduleapp.domain.usecase.GetUserNameUseCase
import com.avcristina.cleancodemultimoduleapp.domain.usecase.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase,
) : ViewModel() {

    private var _stateLiveData = MutableLiveData<MainState>()
    val stateLiveData: LiveData<MainState> = _stateLiveData

    init {
        Log.e("MyLog", "ViewModel created")
        _stateLiveData.value = MainState(saveResult = false, firstName = "", lastName = "")
    }

    // вызывается, когда связанная с ViewModel Activity уничтожается
    override fun onCleared() {
        Log.e("MyLog", "ViewModel cleared")
        super.onCleared()
    }

    fun send(event: MainEvent) {
        when (event) {
            is SaveEvent -> {
                save(text = event.text)
            }

            is LoadEvent -> {
                load()
            }
        }
    }

    private fun save(text: String) {
        val param = SaveUserNameParam(name = text)
        val resultData = saveUserNameUseCase.execute(param = param)
        _stateLiveData.value = MainState(
            saveResult = resultData,
            firstName = stateLiveData.value!!.firstName,
            lastName = stateLiveData.value!!.lastName
        )
    }

    private fun load() {
        val userName: UserName = getUserNameUseCase.execute()
        _stateLiveData.value = MainState(
            saveResult = stateLiveData.value!!.saveResult,
            firstName = userName.firstName,
            lastName = userName.lastName
        )
    }
}