package com.csapp.sqli.utils

import com.csapp.sqli.utils.LineNumberManager.generateLineNumber
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LineNumberManagerTest {
    @Test
    fun generateLineNumberTest() {
        val expectedOutput = "1\n2\n3\n"
        val actualOutput = generateLineNumber(3)
        Assertions.assertEquals(expectedOutput, actualOutput)
    }
}
