package com.avcristina.cleancodemultimoduleapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    init {
        Log.e("MyLog", "ViewModel created")
    }

    // вызывается, когда связанная с ViewModel Activity уничтожается
    override fun onCleared() {
        Log.e("MyLog", "ViewModel created")
        super.onCleared()
    }
}