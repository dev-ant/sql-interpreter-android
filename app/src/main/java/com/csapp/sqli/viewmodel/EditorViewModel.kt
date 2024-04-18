package com.csapp.sqli.viewmodel

import android.text.Layout
import android.util.Log
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

    // Data binding with Activity_editor.xml #EditText afterTextChanged()
    fun onStatementChanged() {
        val editText = editTextStatement.value
        val layout = editText?.layout
        layout?.let {
            updateLineNumbers(it)
        }
    }

    private fun updateLineNumbers(it: Layout) {
        if (it.lineCount != modelLineNumber.number) {
            modelLineNumber.content = generateLineNumber(it.lineCount)
            modelLineNumber.number = it.lineCount
            _textViewLineNumber.value = modelLineNumber.content
        }
    }

    // Activity_editor.xml과 data binding #버튼 클릭시 실행
    fun execStatement(): Any? {
        return editTextStatement.value?.let {
            if (isQueryStatement(editTextStatement.value.toString())) {
                databaseRepository.execQuery(it.text.toString())
            } else {
                databaseRepository.execStatement(it.text.toString())
            }
        }
    }

    // SQL문이 쿼리인지 검증. 즉 SELECT로 시작하는지 확인
    private fun isQueryStatement(statement: String): Boolean {
        return statement.startsWith("SELECT") or statement.startsWith("select")
    }

    // Line Count를 입력받으면 Line Number을 생성
    private fun generateLineNumber(count: Int): String {
        val stringBuilder = StringBuilder()
        for (i in 1..count) {
            stringBuilder.append("$i\n")
        }
        return stringBuilder.toString()
    }
}
