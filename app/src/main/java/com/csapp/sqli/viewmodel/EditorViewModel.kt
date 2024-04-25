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
            updateLineNumberView(layout)
        }
    }

    // Update line number view when line number updated
    private fun updateLineNumberView(layout: Layout) {
        LineNumberManager.updateLineNumbers(layout)?.let { lineNumber ->
            _textViewLineNumber.value = lineNumber
        }
    }

    // View binding with Activity_editor.xml, Check Editor Activity
    fun execStatement(): Any {
        return viewModelScope.launch(Dispatchers.IO) {
            val statement = _editTextStatement.value?.text.toString()
            val result = databaseRepository.execStatement(statement)
            withContext(Dispatchers.Main) {
                // Update statement result
                _queryResult.value = result
            }
        }
    }
}
