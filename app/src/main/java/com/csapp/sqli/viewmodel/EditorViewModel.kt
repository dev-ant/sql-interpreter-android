package com.csapp.sqli.viewmodel

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csapp.sqli.model.LineNumber
import com.csapp.sqli.repository.DatabaseRepository

class EditorViewModel(private val databaseRepository: DatabaseRepository) : ViewModel() {
    private val lineNumberModel = LineNumber("")
    val statementEditView = MutableLiveData<EditText>()
    val lineNumberView = MutableLiveData<String>()

    fun onStatementChanged() {
        val editText = statementEditView.value
        val layout = editText?.layout
        layout?.let {
            lineNumberModel.content = generateLineNumber(it.lineCount)
            lineNumberView.value = lineNumberModel.content
        }
    }

    fun execStatement(): Any? {
        return statementEditView.value?.let {
            if (isQueryStatement(statementEditView.value.toString())) {
                databaseRepository.execQuery(it.text.toString())
            } else {
                databaseRepository.execStatement(it.text.toString())
            }
        }
    }

    private fun isQueryStatement(statement: String): Boolean {
        return statement.startsWith("SELECT") or statement.startsWith("select")
    }

    private fun generateLineNumber(count: Int): String {
        val stringBuilder = StringBuilder()
        for (i in 1..count) {
            stringBuilder.append("$i\n")
        }
        return stringBuilder.toString()
    }
}
