package com.example.assignmentfourpartone

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ScoreDatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE " + TABLE_NAME +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, score INTEGER, date TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert score
    fun insertScore(name: String?, score: Int, date: String?) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("score", score)
        values.put("date", date)
        db.insert(TABLE_NAME, null, values)
    }

    val topScores: Cursor
        // Get top 10 scores
        get() {
            val db = this.readableDatabase
            return db.rawQuery(
                "SELECT * FROM " + TABLE_NAME +
                        " ORDER BY score DESC LIMIT 10", null
            )
        }

    // Clear history
    fun clearScores() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
    }

    companion object {
        private const val DATABASE_NAME = "leaderboard.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "scores"
    }
}