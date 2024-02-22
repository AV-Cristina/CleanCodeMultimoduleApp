package com.avcristina.cleancodemultimoduleapp.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.avcristina.cleancodemultimoduleapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("MyLog", "Activity created")
        vm = ViewModelProvider(
            this, MainViewModelFactory(this)
        )[MainViewModel::class.java]

        val dataTextView = findViewById<TextView>(R.id.dataTextView)
        val dataEditView = findViewById<EditText>(R.id.dataEditText)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val receiveButton = findViewById<Button>(R.id.receiveButton)

        // подписываемся на изменение данных resultLiveData
        vm.resultLiveData.observe(this, Observer {
            dataTextView.text = it
        })

        sendButton.setOnClickListener {
            // сохраняем данные (имя пользователя) в какое-то хранилище
            val text = dataEditView.text.toString()
            vm.save(text)
        }

        receiveButton.setOnClickListener {
            // запрашиваем данные из какого-то хранилища
            vm.load()
        }
    }
}