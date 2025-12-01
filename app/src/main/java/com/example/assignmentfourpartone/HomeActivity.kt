package com.example.assignmentfourpartone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.btnStartGame).setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        findViewById<Button>(R.id.btnViewLeaderboard).setOnClickListener {
            startActivity(Intent(this, LeaderboardActivity::class.java))
        }
    }
}
