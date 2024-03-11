package com.csapp.sqli.viewmodel

import androidx.lifecycle.ViewModel
import com.csapp.sqli.DatabaseHelper

class EditorViewModel(private val databaseHelper: DatabaseHelper) : ViewModel() {

    // private val _toDoList = MutableLiveData<List<ToDo>>()
    // val toDoList: LiveData<List<ToDo>> get() = _toDoList

    fun executeQuery(sql: String): Any? {
        return if (sql.startsWith("SELECT")) {
            databaseHelper.execQueryReturn(sql)
        } else {
            databaseHelper.execQueryNoReturn(sql)
        }
    }
}