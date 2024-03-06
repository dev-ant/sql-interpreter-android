package com.csapp.sqli.view.editor

import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.csapp.sqli.R

class EditorActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        editText = findViewById(R.id.edittext_query_editor)
        textView = findViewById(R.id.textview_query_editor_liner)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // "Not yet implemented"
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // "Not yet implemented"
            }

            override fun afterTextChanged(p0: Editable?) {
                val lineCount = getLineCount(editText.text.toString())
                setEditorLiner(lineCount)
            }
        })
    }

    private fun getLineCount(text: String): Int {
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
}