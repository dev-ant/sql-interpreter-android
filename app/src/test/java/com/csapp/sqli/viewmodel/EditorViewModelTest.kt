package com.csapp.sqli.viewmodel

import android.widget.EditText
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.csapp.sqli.repository.DatabaseRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EditorViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: EditorViewModel
    private lateinit var databaseRepository: DatabaseRepository

    @Before
    fun setUp() {
        databaseRepository = mockk()
        viewModel = EditorViewModel(databaseRepository)
    }

    @Test
    fun `onStatementChanged should update lineNumberView with correct line numbers`() {
        // Given
        val expectedLineNumbers = "1\n2\n3\n" // Assuming 3 lines of text
        val mockEditText =
            mockk<EditText>().apply {
                every { layout.lineCount } returns 3
            }
        viewModel.statementEditView.value = mockEditText

        // When
        viewModel.onStatementChanged()

        // Then
        assertEquals(expectedLineNumbers, viewModel.lineNumberView.value)
    }
}
