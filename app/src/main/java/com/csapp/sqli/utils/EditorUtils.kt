package com.csapp.sqli.utils

import android.annotation.SuppressLint
import android.database.Cursor
import android.text.Layout
import android.widget.TableRow
import android.widget.TextView
import com.csapp.sqli.databinding.ActivityEditorBinding

object EditorUtils {

    private const val DEFAULT_TEXT_SIZE = 24f
    private const val DEFAULT_PADDING = 10

    fun setEditorLiner(binding: ActivityEditorBinding) {
        val layout: Layout = binding.edittextQueryEditor.layout
        val text = binding.edittextQueryEditor.text
        val count = layout.getLineForOffset(text.length) + 1
        val stringBuilder = StringBuilder()

        for (i in 1..count) {
            stringBuilder.append("$i\n")
        }
        binding.textviewQueryEditorLiner.text = stringBuilder.toString()
    }

    private fun TableRow.addViewWithParams(view: TextView) {
        val screenWidth = resources.displayMetrics.widthPixels
        val params = TableRow.LayoutParams(
            screenWidth,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = params
        this.addView(view)
    }

    private fun TextView.applyCommonProperties() {
        textSize = DEFAULT_TEXT_SIZE
        setPadding(
            DEFAULT_PADDING,
            DEFAULT_PADDING,
            DEFAULT_PADDING,
            DEFAULT_PADDING
        )
    }

    fun displayResultOrMessage(binding: ActivityEditorBinding, result: Any?) {
        result?.let { nonNullResult ->
            when (nonNullResult) {
                is Cursor -> displayResult(binding, nonNullResult)
                is String -> displayMessage(binding, nonNullResult)
            }
        }
    }

    @SuppressLint("Range")
    private fun displayResult(binding: ActivityEditorBinding, cursor: Cursor) {
        binding.tableQueryExecuteResult.removeAllViews()

        val headerRow = TableRow(binding.root.context)
        val columns = cursor.columnNames
        for (column in columns) {
            val textView = TextView(binding.root.context)
            textView.text = column
            textView.applyCommonProperties()
            headerRow.addView(textView)
        }
        binding.tableQueryExecuteResult.addView(headerRow)

        while (cursor.moveToNext()) {
            val dataRow = TableRow(binding.root.context)
            for (column in columns) {
                val textView = TextView(binding.root.context)
                textView.text = cursor.getString(cursor.getColumnIndex(column))
                textView.applyCommonProperties()
                dataRow.setPadding(
                    DEFAULT_PADDING,
                    DEFAULT_PADDING,
                    DEFAULT_PADDING,
                    DEFAULT_PADDING
                )
                dataRow.addView(textView)
            }
            binding.tableQueryExecuteResult.addView(dataRow)
        }
    }

    private fun displayMessage(binding: ActivityEditorBinding, msg: String) {
        binding.tableQueryExecuteResult.removeAllViews()
        val row = TableRow(binding.root.context)
        val textView = TextView(binding.root.context)
        textView.text = msg
        textView.applyCommonProperties()
        textView.maxLines = Int.MAX_VALUE
        textView.isSingleLine = false
        row.setPadding(
            DEFAULT_PADDING,
            DEFAULT_PADDING,
            DEFAULT_PADDING,
            DEFAULT_PADDING,
        )
        row.addViewWithParams(textView)
        binding.tableQueryExecuteResult.addView(row)
    }
}