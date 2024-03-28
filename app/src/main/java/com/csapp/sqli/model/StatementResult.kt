package com.csapp.sqli.model

class StatementResult(private var result: String = "") {
    fun delete() {
        result = ""
    }
}
