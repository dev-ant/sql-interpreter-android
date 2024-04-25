package com.csapp.sqli.utils

import android.text.Layout
import com.csapp.sqli.model.LineNumber

object LineNumberManager {
    private val modelLineNumber = LineNumber()

    // If line number changed, update model and render line number text view
    fun updateLineNumbers(it: Layout): String? {
        if (it.lineCount != modelLineNumber.number) {
            modelLineNumber.content = generateLineNumber(it.lineCount)
            modelLineNumber.number = it.lineCount
            return modelLineNumber.content
        }
        return null
    }

    // Generate line number like ""1/n2/n3/n ... count"
    fun generateLineNumber(count: Int): String {
        return buildString {
            for (i in 1..count) {
                append("$i\n")
            }
        }
    }
}
