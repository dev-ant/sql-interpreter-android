package com.csapp.sqli.model

class QueryResult(private var result: String = "") {

    fun delete() {
        result = ""
    }
}