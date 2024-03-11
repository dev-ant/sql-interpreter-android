package com.csapp.sqli.view

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.csapp.sqli.DatabaseHelper
import com.csapp.sqli.databinding.ActivityEditorBinding
import com.csapp.sqli.viewmodel.EditorViewModel

class EditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditorBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var viewModel: EditorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        viewModel = EditorViewModel(databaseHelper)

        binding.edittextQueryEditor.addTextChangedListener(object : TextWatcher {
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

        binding.btnQueryEditorRun.setOnClickListener {
            val sql = binding.edittextQueryEditor.text.toString()
            val result = viewModel.executeQuery(sql)
            if (result != null) {
                displayResultOrMessage(result)
            }
        }
    }

    private fun getLineCount(): Int {
        val layout: Layout = binding.edittextQueryEditor.layout
        val text = binding.edittextQueryEditor.text
        return layout.getLineForOffset(text.length) + 1
    }

    private fun setEditorLiner(count: Int) {
        val stringBuilder = StringBuilder()
        for (i in 1..count) {
            stringBuilder.append("$i\n")
        }

        binding.textviewQueryEditorLiner.text = stringBuilder.toString()
    }

    private fun displayResultOrMessage(result: Any?) {
        result?.let { nonNullResult ->
            when (nonNullResult) {
                is Cursor -> displayResult(nonNullResult)
                is String -> displayMessage(nonNullResult)
            }
        }
    }

    @SuppressLint("Range")
    private fun displayResult(cursor: Cursor) {
        binding.tableQueryExecuteResult.removeAllViews()

        val headerRow = TableRow(this)
        val columns = cursor.columnNames
        for (column in columns) {
            val textView = TextView(this)
            textView.text = column
            textView.textSize = 24f
            headerRow.setPadding(10, 10, 10, 10)
            headerRow.addView(textView)
        }
        binding.tableQueryExecuteResult.addView(headerRow)

        while (cursor.moveToNext()) {
            val dataRow = TableRow(this)
            for (column in columns) {
                val textView = TextView(this)
                textView.text = cursor.getString(cursor.getColumnIndex(column))
                textView.textSize = 24f
                dataRow.setPadding(10, 10, 10, 10)
                dataRow.addView(textView)
            }
            binding.tableQueryExecuteResult.addView(dataRow)
        }
    }

    private fun displayMessage(msg: String) {
        binding.tableQueryExecuteResult.removeAllViews()
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
        binding.tableQueryExecuteResult.addView(row)
    }
}