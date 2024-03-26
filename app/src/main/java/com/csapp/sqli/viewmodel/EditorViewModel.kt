package com.csapp.sqli.viewmodel

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csapp.sqli.DatabaseHelper

class EditorViewModel(private val databaseHelper: DatabaseHelper) : ViewModel() {

    val inputQuery = MutableLiveData<EditText>()
    val lineNumber = MutableLiveData<String>()

    fun onTextChanged() {
        val editText = inputQuery.value ?: return
        val layout = editText.layout ?: return
        val count = layout.lineCount
        lineNumber.value = count.toString()
    }

    fun executeQuery() {
        if (isStartWithSELECT(inputQuery.value.toString())) {
            inputQuery.value?.let { databaseHelper.execQueryReturn(it.text.toString()) }
        } else {
            inputQuery.value?.let { databaseHelper.execQueryNoReturn(it.text.toString()) }
        }
    }

    private fun isStartWithSELECT(statement: String): Boolean {
        return statement.startsWith("SELECT") or statement.startsWith("select")
    }
}
