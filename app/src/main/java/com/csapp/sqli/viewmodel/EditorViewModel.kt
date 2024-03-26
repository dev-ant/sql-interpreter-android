package com.csapp.sqli.viewmodel

import android.database.Cursor
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.csapp.sqli.DatabaseHelper
import com.csapp.sqli.model.LineNumber
import com.csapp.sqli.utils.ViewUtils.addViewWithParams
import com.csapp.sqli.utils.ViewUtils.applyCommonProperties

class EditorViewModel(private val databaseHelper: DatabaseHelper) : ViewModel() {

    private val lineNumberModel = LineNumber("")
    val inputQuery = MutableLiveData<EditText>()
    val lineNumber = MutableLiveData<String>()
    val resultTable = MutableLiveData<TableLayout>()

    fun onTextChanged() {
        lineNumberModel.content = setLineNumber()
        lineNumber.value = lineNumberModel.content
    }

    fun runOnClick() {
        displayResultOrMessage(executeQuery())
    }

    private fun executeQuery(): Any? {
        return inputQuery.value?.let {
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

    private fun displayResultOrMessage(
        result: Any?,
    ) {
        result?.let { nonNullResult ->
            when (nonNullResult) {
                is Cursor -> displayResult(nonNullResult)
                is String -> displayMessage(nonNullResult)
            }
        }
    }

    private fun displayResult(
        cursor: Cursor,
    ) {
        
    }

    private fun displayMessage(
        msg: String,
    ) {

    }
}
