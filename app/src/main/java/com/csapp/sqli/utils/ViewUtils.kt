package com.csapp.sqli.utils

import android.widget.TableRow
import android.widget.TextView

object ViewUtils {

    private const val DEFAULT_TEXT_SIZE = 20f
    private const val DEFAULT_PADDING = 10

    fun TableRow.addViewWithParams(view: TextView) {
        val screenWidth = resources.displayMetrics.widthPixels
        val params = TableRow.LayoutParams(
            screenWidth,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = params
        this.layoutParams = params
        this.addView(view)
    }

    fun TextView.applyCommonProperties() {
        textSize = DEFAULT_TEXT_SIZE
        setPadding(
            DEFAULT_PADDING,
            DEFAULT_PADDING,
            DEFAULT_PADDING,
            DEFAULT_PADDING
        )
    }
}