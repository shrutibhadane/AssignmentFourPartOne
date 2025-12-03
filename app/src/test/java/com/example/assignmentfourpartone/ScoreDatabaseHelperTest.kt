package com.example.assignmentfourpartone

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ScoreDatabaseHelperTest {

    private lateinit var dbHelper: ScoreDatabaseHelper
    private lateinit var database: SQLiteDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        dbHelper = ScoreDatabaseHelper(context)
        database = dbHelper.writableDatabase
        database.delete("scores", null, null)
    }

    @After
    fun teardown() {
        dbHelper.close()
    }

    @Test
    fun `insertScore should return true when data inserted successfully`() {
        val result = dbHelper.insertScore("Shruti", 10, "01/01/2025 10:10:10")
        assertTrue(result)
    }

    @Test
    fun `database should contain the inserted score`() {
        dbHelper.insertScore("Shruti", 50, "01/01/2025 11:00:00")

        val cursor = database.rawQuery("SELECT * FROM scores WHERE score = 50", null)

        assertNotNull(cursor)
        assertTrue(cursor.moveToFirst())

        assertEquals("Shruti", cursor.getString(cursor.getColumnIndex("name")))
        assertEquals(50, cursor.getInt(cursor.getColumnIndex("score")))
        assertEquals("01/01/2025 11:00:00", cursor.getString(cursor.getColumnIndex("date")))

        cursor.close()
    }

    @Test
    fun `multiple insert should increase row count`() {
        dbHelper.insertScore("Shruti", 30, "01/01/2025 10:00:00")
        dbHelper.insertScore("Amit", 40, "01/01/2025 10:05:00")

        val cursor = database.rawQuery("SELECT COUNT(*) FROM scores", null)
        cursor.moveToFirst()

        val count = cursor.getInt(0)
        assertEquals(2, count)

        cursor.close()
    }
}
