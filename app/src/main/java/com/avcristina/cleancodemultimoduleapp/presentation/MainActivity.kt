package com.avcristina.cleancodemultimoduleapp.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.avcristina.cleancodemultimoduleapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("MyLog", "Activity created")

        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val dataEditView = findViewById<EditText>(R.id.dataEditText)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val receiveButton = findViewById<Button>(R.id.receiveButton)

        // подписываемся на изменение данных resultLiveData
        vm.stateLiveData.observe(this, Observer { state ->
            dataTextView.text = "${state.firstName} ${state.lastName} ${state.saveResult}"
        })

        sendButton.setOnClickListener {
            // сохраняем данные (имя пользователя) в какое-то хранилище
            val text = dataEditView.text.toString()
            vm.send(SaveEvent(text = text))
        }

        receiveButton.setOnClickListener {
            // запрашиваем данные из какого-то хранилища
            vm.send(LoadEvent())
        }
    }
}