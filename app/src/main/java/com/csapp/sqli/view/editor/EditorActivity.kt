package com.csapp.sqli.view.editor

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.csapp.sqli.DatabaseHelper
import com.csapp.sqli.R

class EditorActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private lateinit var buttonRun: Button
    private lateinit var tableResult: TableLayout
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        editText = findViewById(R.id.edittext_query_editor)
        textView = findViewById(R.id.textview_query_editor_liner)
        buttonRun = findViewById(R.id.btn_query_editor_run)
        databaseHelper = DatabaseHelper(this)
        tableResult = findViewById(R.id.table_query_execute_result)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // "Not yet implemented"
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // "Not yet implemented"
            }

            override fun afterTextChanged(p0: Editable?) {
                val lineCount = getLineCount()
                setEditorLiner(lineCount)
            }
        })

        buttonRun.setOnClickListener {
            val sql = editText.text.toString()
            if (sql.startsWith("SELECT")) {
                databaseHelper.execQueryReturn(editText.text.toString())?.let { cursor ->
                    displayResult(cursor)
                    cursor.close()
                }
            } else {
                Log.i("DB_HELPER", "msg")
                displayMessage(databaseHelper.execQueryNoReturn(sql))
            }
        }
    }

    private fun getLineCount(): Int {
        val layout: Layout = editText.layout
        val text = editText.text
        return layout.getLineForOffset(text.length) + 1
    }

    private fun setEditorLiner(count: Int) {
        val stringBuilder = StringBuilder()
        for (i in 1..count) {
            stringBuilder.append("$i\n")
        }

        textView.text = stringBuilder.toString()
    }

    @SuppressLint("Range")
    private fun displayResult(cursor: Cursor) {
        tableResult.removeAllViews()

        val headerRow = TableRow(this)
        val columns = cursor.columnNames
        for (column in columns) {
            val textView = TextView(this)
            textView.text = column
            textView.textSize = 24f
            headerRow.setPadding(10, 10, 10, 10)
            headerRow.addView(textView)
        }
        tableResult.addView(headerRow)

        while (cursor.moveToNext()) {
            val dataRow = TableRow(this)
            for (column in columns) {
                val textView = TextView(this)
                textView.text = cursor.getString(cursor.getColumnIndex(column))
                textView.textSize = 24f
                dataRow.setPadding(10, 10, 10, 10)
                dataRow.addView(textView)
            }
            tableResult.addView(dataRow)
        }
    }

    private fun displayMessage(msg: String) {
        tableResult.removeAllViews()
        val row = TableRow(this)
        val textView = TextView(this)
        textView.text = msg
        textView.textSize = 24f
        textView.maxLines = Int.MAX_VALUE
        textView.isSingleLine = false

        val screenWidth = resources.displayMetrics.widthPixels
        val params = TableRow.LayoutParams(
            screenWidth,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = params

        row.setPadding(10, 10, 10, 10)
        row.addView(textView)
        tableResult.addView(row)
    }
}