package com.csapp.sqli.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.csapp.sqli.DatabaseHelper
import com.csapp.sqli.databinding.ActivityEditorBinding
import com.csapp.sqli.utils.EditorUtils
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

        setupListeners()
    }

    private fun setupListeners() {
        binding.edittextQueryEditor.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // "Not yet implemented"
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // "Not yet implemented"
            }

            override fun afterTextChanged(p0: Editable?) {
                EditorUtils.setEditorLiner(binding)
            }
        })

        binding.btnQueryEditorRun.setOnClickListener {
            val sql = binding.edittextQueryEditor.text.toString()
            val result = viewModel.executeQuery(sql)
            if (result != null) {
                EditorUtils.displayResultOrMessage(binding, result)
            }
        }
    }
}
