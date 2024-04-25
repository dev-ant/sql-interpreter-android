package com.csapp.sqli.viewmodel

import android.text.Layout
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csapp.sqli.repository.DatabaseRepository
import com.csapp.sqli.utils.LineNumberManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditorViewModel(private val databaseRepository: DatabaseRepository) : ViewModel() {
    private val _editTextStatement = MutableLiveData<EditText>()
    val editTextStatement: MutableLiveData<EditText> = _editTextStatement

    private val _textViewLineNumber = MutableLiveData<String>()
    val textViewLineNumber: LiveData<String> = _textViewLineNumber

    private val _queryResult = MutableLiveData<Any?>()
    val queryResult: LiveData<Any?> = _queryResult

    // Data binding with Activity_editor.xml #afterTextChanged()
    fun onStatementChanged() {
        editTextStatement.value?.layout?.let { layout ->
            renderLineNumberView(layout)
        }
    }

    // Render line number view when line number updated
    private fun renderLineNumberView(layout: Layout) {
        LineNumberManager.updateLineNumbers(layout)?.let { lineNumber ->
            _textViewLineNumber.value = lineNumber
        }
    }

    // View binding with Activity_editor.xml, Check Editor Activity
    fun execStatement(): Any? {
        return viewModelScope.launch(Dispatchers.IO) {
            val result =
                withContext(Dispatchers.IO) {
                    _editTextStatement.value?.let {
                        if (isSelectStatement(editTextStatement.value.toString())) {
                            databaseRepository.execSelectStatement(it.text.toString())
                        } else {
                            databaseRepository.execStatement(it.text.toString())
                        }
                    }
                }
            withContext(Dispatchers.Main) {
                _queryResult.value = result
            }
        }
    }

    // Check if a statement starts with "SELECT" ignore case
    private fun isSelectStatement(statement: String): Boolean {
        return statement.startsWith("SELECT", ignoreCase = true)
    }
}
