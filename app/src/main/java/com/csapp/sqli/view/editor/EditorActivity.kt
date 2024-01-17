package com.csapp.sqli.view.editor

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.widget.EditText
import com.csapp.sqli.R

class EditorActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        editText = findViewById(R.id.edittext_query_editor)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Not yet implemented
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Not yet implemented
            }

            override fun afterTextChanged(editable: Editable) {
                println("askjdnaksd")
                val keyword = "SELECT"
                highlightKeyword(editable, keyword, Color.RED)
            }
        })
    }

    private fun highlightKeyword(editable: Editable, keyword: String, color: Int) {
        val spans = editable.getSpans(0, editable.length, ForegroundColorSpan::class.java)

        for (span in spans) {
            editable.removeSpan(span)
        }

        var index = editable.toString().indexOf(keyword)
        while (index >= 0) {
            editable.setSpan(ForegroundColorSpan(color), index, index + keyword.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            index = editable.toString().indexOf(keyword, index + keyword.length)
        }
    }
}