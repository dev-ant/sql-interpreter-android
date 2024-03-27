package com.csapp.sqli.viewmodel

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csapp.sqli.model.LineNumber
import com.csapp.sqli.repository.DatabaseRepository

class EditorViewModel(private val databaseRepository: DatabaseRepository) : ViewModel() {
    private val lineNumberModel = LineNumber("")
    val inputQuery = MutableLiveData<EditText>()
    val lineNumber = MutableLiveData<String>()

    fun onTextChanged() {
        lineNumberModel.content = setLineNumber()
        lineNumber.value = lineNumberModel.content
    }

    fun executeQuery(): Any? {
        return inputQuery.value?.let {
            if (isStartWithSELECT(inputQuery.value.toString())) {
                databaseRepository.execQueryReturn(it.text.toString())
            } else {
                databaseRepository.execQueryNoReturn(it.text.toString())
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
