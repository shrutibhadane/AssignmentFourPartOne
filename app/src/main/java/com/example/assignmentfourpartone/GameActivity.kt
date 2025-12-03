package com.example.assignmentfourpartone

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var tvQuestion: TextView
    private lateinit var tvScore: TextView
    private lateinit var tvTimer: TextView
    private lateinit var btnOption1: Button
    private lateinit var btnOption2: Button
    private lateinit var btnOption3: Button
    private lateinit var btnOption4: Button
    private var score = 0
    private var correctAnswer = 0
    private var timeLeft = 30
    private var timer: CountDownTimer? = null
    private val optionButtons = mutableListOf<Button>()

    var isTestMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        initializeViews()
        startTimer()
        generateQuestion()
    }

    private fun initializeViews() {
        tvQuestion = findViewById(R.id.tvQuestion)
        tvScore = findViewById(R.id.tvScore)
        tvTimer = findViewById(R.id.tvTimer)
        btnOption1 = findViewById(R.id.btnOption1)
        btnOption2 = findViewById(R.id.btnOption2)
        btnOption3 = findViewById(R.id.btnOption3)
        btnOption4 = findViewById(R.id.btnOption4)

        optionButtons.addAll(listOf(btnOption1, btnOption2, btnOption3, btnOption4))

        // Set click listeners for all option buttons
        optionButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                checkAnswer(button.text.toString().toInt(), button)
            }
        }
    }

    private fun startTimer() {
        if (isTestMode) return
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = (millisUntilFinished / 1000).toInt()
                tvTimer.text = timeLeft.toString()

                // Change timer color when time is running out
                if (timeLeft <= 10) {
                    tvTimer.setTextColor(
                        ContextCompat.getColor(
                            this@GameActivity,
                            R.color.red_600
                        )
                    )
                }
            }

            override fun onFinish() {
                gameOver()
            }
        }.start()
    }

    private fun generateQuestion() {
        // Generate two random numbers between 1 and 20
        val num1 = Random.nextInt(1, 21)
        val num2 = Random.nextInt(1, 21)

        // Select a random operator
        val operators = listOf("+", "-", "×")
        val operator = operators.random()

        // Calculate the correct answer
        correctAnswer = when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "×" -> num1 * num2
            else -> 0
        }

        // Display the question
        tvQuestion.text = "$num1 $operator $num2 = ?"

        // Generate options (1 correct + 3 incorrect)
        val options = generateOptions(correctAnswer)

        // Shuffle and display options
        val shuffledOptions = options.shuffled()
        optionButtons.forEachIndexed { index, button ->
            button.text = shuffledOptions[index].toString()
            button.isEnabled = true
            button.setBackgroundResource(R.drawable.rounded_button_purple)
        }
    }

    private fun generateOptions(correct: Int): List<Int> {
        val options = mutableSetOf<Int>()
        options.add(correct)

        // Generate 3 unique incorrect answers
        while (options.size < 4) {
            val offset = Random.nextInt(-10, 11)
            val incorrectAnswer = correct + offset
            if (incorrectAnswer != correct && incorrectAnswer > 0) {
                options.add(incorrectAnswer)
            }
        }

        return options.toList()
    }

    private fun checkAnswer(selectedAnswer: Int, selectedButton: Button) {

        // Disable all buttons to prevent multiple clicks
        optionButtons.forEach { it.isEnabled = false }

        if (selectedAnswer == correctAnswer) {
            // Correct answer -> make the selected button green
            selectedButton.setBackgroundResource(R.drawable.rounded_button_green)

            score += 10
            tvScore.text = score.toString()

            // Load next question after delay
            selectedButton.postDelayed({
                generateQuestion()
            }, 800)

        } else {
            // Wrong answer -> make selected button red
            selectedButton.setBackgroundResource(R.drawable.rounded_button_red)

            // Highlight the correct answer in green
            optionButtons.forEach { button ->
                if (button.text.toString().toInt() == correctAnswer) {
                    button.setBackgroundResource(R.drawable.rounded_button_green)
                }
            }

            // End game after brief display
            selectedButton.postDelayed({
                gameOver()
            }, 1000)
        }
    }


    private fun gameOver() {
        timer?.cancel()
        val intent = Intent(this, GameOverActivity::class.java)
        intent.putExtra("FINAL_SCORE", score)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}