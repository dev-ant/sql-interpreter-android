package com.csapp.sqli.viewmodel

import androidx.lifecycle.ViewModel
import com.csapp.sqli.DatabaseHelper

class EditorViewModel(private val databaseHelper: DatabaseHelper) : ViewModel() {
    fun executeQuery(sql: String): Any {
        return if (sql.startsWith("SELECT") or sql.startsWith("select")) {
            databaseHelper.execQueryReturn(sql)
        } else {
            databaseHelper.execQueryNoReturn(sql)
        }
    }
}
