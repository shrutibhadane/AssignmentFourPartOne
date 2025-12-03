package com.example.assignmentfourpartone

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class GameOverActivity : AppCompatActivity() {

    private lateinit var tvYourScoreValue: TextView
    private lateinit var btnPlayAgain: Button
    private lateinit var btnSaveScore: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        tvYourScoreValue = findViewById(R.id.tvYourScoreValue)
        btnPlayAgain = findViewById(R.id.btnPlayAgain)
        btnSaveScore = findViewById(R.id.btnSaveScore)

        // Get the final score from intent
        val finalScore = intent.getIntExtra("FINAL_SCORE", 0)
        tvYourScoreValue.text = finalScore.toString()

        // Play Again button
        btnPlayAgain.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            finish()
        }

        /*Toast.makeText(this, "Score saved $isInserted", Toast.LENGTH_SHORT).show()
           val intent = Intent(this, LeaderboardActivity::class.java)
           startActivity(intent)
           finish()*/

        btnSaveScore.setOnClickListener {
            val currentDateTime =
                SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())

            val db = ScoreDatabaseHelper(this)
            val isInserted = db.insertScore("Shruti", finalScore, currentDateTime)

            if (isInserted) {
                Toast.makeText(this, "Score saved", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LeaderboardActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Failed to save score", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onBackPressed() {
        // Prevent going back to game activity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}
