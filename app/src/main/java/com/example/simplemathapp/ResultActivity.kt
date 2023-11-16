package com.example.simplemathapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Visa antal rätt svar när 10 frågor har besvarats
        val correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)
        val accuracy = (correctAnswers.toDouble() / 10) * 100 // Räkna ut procentuellt rätt
        val resultMessage = "Du fick $correctAnswers av 10 rätt. $accuracy% korrekt."

        val resultView = findViewById<TextView>(R.id.resultView)
        resultView.text = resultMessage

        val restartButton = findViewById<Button>(R.id.restartButton)
        restartButton.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}