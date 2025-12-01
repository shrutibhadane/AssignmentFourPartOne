package com.example.assignmentfourpartone

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnClear: Button
    private lateinit var btnBackHome: Button
    private lateinit var adapter: ScoreAdapter
    private lateinit var dbHelper: ScoreDatabaseHelper
    private val scoreList: MutableList<ScoreModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        recyclerView = findViewById(R.id.recyclerViewScores)
        recyclerView.layoutManager = LinearLayoutManager(this)

        dbHelper = ScoreDatabaseHelper(this)
        loadScores()

        btnClear = findViewById(R.id.btnClearHistory)
        btnClear.setOnClickListener { _: View? ->
            dbHelper.clearScores()
            scoreList.clear()
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "History cleared!", Toast.LENGTH_SHORT).show()
        }

        btnBackHome = findViewById(R.id.btnBackHome)
        btnBackHome.setOnClickListener { _: View? ->
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loadScores() {
        val cursor = dbHelper.topScores
        scoreList.clear()
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val score = cursor.getInt(cursor.getColumnIndexOrThrow("score"))
                val date = cursor.getString(cursor.getColumnIndexOrThrow("date"))
                scoreList.add(ScoreModel(name, score, date))
            } while (cursor.moveToNext())
        }
        cursor.close()

        adapter = ScoreAdapter(scoreList)
        recyclerView.adapter = adapter
    }
}