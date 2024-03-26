package com.csapp.sqli.viewmodel

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csapp.sqli.DatabaseHelper
import com.csapp.sqli.model.LineNumber

class EditorViewModel(private val databaseHelper: DatabaseHelper) : ViewModel() {

    private val lineNumberModel = LineNumber("")
    val inputQuery = MutableLiveData<EditText>()
    val lineNumber = MutableLiveData<String>()

    fun onTextChanged() {
        lineNumberModel.content = setLineNumber()
        lineNumber.value = lineNumberModel.content
    }

    fun executeQuery() {
        inputQuery.value?.let {
            if (isStartWithSELECT(inputQuery.value.toString())) {
                databaseHelper.execQueryReturn(it.text.toString())
            } else {
                databaseHelper.execQueryNoReturn(it.text.toString())
            }
        }
    }

    private fun isStartWithSELECT(statement: String): Boolean {
        return statement.startsWith("SELECT") or statement.startsWith("select")
    }

    private fun setLineNumber(): String {
        val editText = inputQuery.value
        val layout = editText?.layout
        val count = layout?.lineCount
        val stringBuilder = StringBuilder()
        for (i in 1..count!!) {
            stringBuilder.append("$i\n")
        }
        return stringBuilder.toString()
    }
}
