package com.csapp.sqli.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.csapp.sqli.DatabaseHelper

@Suppress("UNCHECKED_CAST")
class EditorViewModelFactory(private val context: Context) : Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditorViewModel::class.java)) {
            val databaseHelper = DatabaseHelper(context)
            return EditorViewModel(databaseHelper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}