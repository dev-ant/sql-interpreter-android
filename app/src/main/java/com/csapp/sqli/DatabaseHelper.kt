package com.csapp.sqli

import android.annotation.SuppressLint
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


    fun execQueryNoReturn(sql: String): String {
        val database = this.writableDatabase
        return try {
            database.execSQL(sql)
            Log.i(TAG, MSG.SUCCESS + sql)
            MSG.SUCCESS
        } catch (e: Exception) {
            Log.e(TAG, MSG.ERROR + e.message)
            MSG.ERROR + e.message
        } finally {
            database.close()
        }
    }

    @SuppressLint("Recycle")
    fun execQueryReturn(sql: String): Any {
        val database = this.readableDatabase
        return try {
            val cursor = database.rawQuery(sql, null)
            Log.i(TAG, MSG.SUCCESS)
            cursor
        } catch (e: Exception) {
            Log.e(TAG, MSG.ERROR + "${e.message}")
            MSG.ERROR + e.message
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