package com.avcristina.cleancodemultimoduleapp.presentation

interface MainEvent

class SaveEvent(val text: String) : MainEvent

class LoadEvent : MainEvent