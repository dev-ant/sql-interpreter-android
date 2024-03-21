package com.csapp.sqli.utils

import android.widget.TableRow
import android.widget.TextView

object ViewUtils {
    private const val DEFAULT_TEXT_SIZE = 20f
    private const val DEFAULT_PADDING = 10

    fun TableRow.addViewWithParams(
        view: TextView,
        matchParentWidth: Boolean = false,
    ) {
        this.layoutParams =
            TableRow.LayoutParams(
                resources.displayMetrics.widthPixels,
                TableRow.LayoutParams.WRAP_CONTENT,
            )
        if (matchParentWidth) {
            view.applyScreenWidth()
        }
        this.addView(view)
    }

    private fun TextView.applyScreenWidth() {
        this.maxLines = Int.MAX_VALUE
        this.isSingleLine = false
        this.layoutParams =
            TableRow.LayoutParams(
                resources.displayMetrics.widthPixels,
                TableRow.LayoutParams.WRAP_CONTENT,
            )
    }

    fun TextView.applyCommonProperties() {
        textSize = DEFAULT_TEXT_SIZE
        setPadding(
            DEFAULT_PADDING,
            DEFAULT_PADDING,
            DEFAULT_PADDING,
            DEFAULT_PADDING,
        )
    }
}
