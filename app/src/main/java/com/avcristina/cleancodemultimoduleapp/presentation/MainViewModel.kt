package com.avcristina.cleancodemultimoduleapp.presentation

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

    private var _resultLiveData = MutableLiveData<String>()
    val resultLiveData: LiveData<String> = _resultLiveData

    fun save(text: String) {
        val param = SaveUserNameParam(name = text)
        val resultData = saveUserNameUseCase.execute(param = param)
        _resultLiveData.value = "Save result = $resultData"
    }

    fun load() {
        val userName: UserName = getUserNameUseCase.execute()
        _resultLiveData.value = "${userName.firstName} ${userName.lastName}"
    }

//    init {
//        Log.e("MyLog", "ViewModel created")
//    }
//
//    // вызывается, когда связанная с ViewModel Activity уничтожается
//    override fun onCleared() {
//        Log.e("MyLog", "ViewModel cleared")
//        super.onCleared()
//    }
}