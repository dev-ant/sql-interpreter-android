package com.csapp.sqli

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(p0: SQLiteDatabase?) {
        // Not yet implemented
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // Not yet implemented
    }

    fun execQuery(sql: String): Cursor? {
        return if (sql.startsWith("SELECT")) {
            execQueryReturn(sql)
        } else {
            execQueryNoReturn(sql)
            null
        }
    }

    private fun execQueryNoReturn(sql: String) {
        val database = this.writableDatabase
        try {
            database.execSQL(sql)
            Log.i(TAG, MSG.SUCCESS)
        } catch (e: Exception) {
            Log.e(TAG, MSG.ERROR + "${e.message}")
        } finally {
            database.close()
        }
    }

    private fun execQueryReturn(sql: String): Cursor? {
        val database = this.readableDatabase
        return try {
            val cursor = database.rawQuery(sql, null)
            Log.i(TAG, MSG.SUCCESS)
            cursor
        } catch (e: Exception) {
            Log.e(TAG, MSG.ERROR + "${e.message}")
            null
        }
    }

    companion object {
        private const val TAG = "DB_HELPER"
        private const val DATABASE_NAME = "SQLInterpreter.db"
        private const val DATABASE_VERSION = 1

        private object MSG {
            const val SUCCESS = "Query executed successfully"
            const val ERROR = "Error executing query: "
        }
    }
}