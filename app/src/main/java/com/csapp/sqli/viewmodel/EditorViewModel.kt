package com.csapp.sqli.viewmodel

import android.text.Layout
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csapp.sqli.model.LineNumber
import com.csapp.sqli.repository.DatabaseRepository

class EditorViewModel(private val databaseRepository: DatabaseRepository) : ViewModel() {
    private val modelLineNumber = LineNumber()

    private val _editTextStatement = MutableLiveData<EditText>()
    val editTextStatement: MutableLiveData<EditText> = _editTextStatement

    private val _textViewLineNumber = MutableLiveData<String>()
    val textViewLineNumber: LiveData<String> = _textViewLineNumber

    // Data binding with Activity_editor.xml #afterTextChanged()
    fun onStatementChanged() {
        val editText = editTextStatement.value
        editText?.layout?.let { updateLineNumbers(it) }
    }

    // If line number changed, update model and render line number text view
    private fun updateLineNumbers(it: Layout) {
        if (it.lineCount != modelLineNumber.number) {
            modelLineNumber.content = generateLineNumber(it.lineCount)
            modelLineNumber.number = it.lineCount
            _textViewLineNumber.value = modelLineNumber.content
        }
    }

    // View binding with Activity_editor.xml, Check Editor Activity
    fun execStatement(): Any? {
        return _editTextStatement.value?.let {
            if (isQueryStatement(editTextStatement.value.toString())) {
                databaseRepository.execQuery(it.text.toString())
            } else {
                databaseRepository.execStatement(it.text.toString())
            }
        }
    }

    // Check if a statement starts with "SELECT" ignore case
    private fun isQueryStatement(statement: String): Boolean {
        return statement.startsWith("SELECT", ignoreCase = true)
    }

    // Generate line number like ""1/n2/n3/n ... count"
    private fun generateLineNumber(count: Int): String {
        val stringBuilder = StringBuilder()
        for (i in 1..count) {
            stringBuilder.append("$i\n")
        }
        return stringBuilder.toString()
    }
}
