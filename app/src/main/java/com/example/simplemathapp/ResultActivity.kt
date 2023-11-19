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

        // Ta emot värden från MainActivity
        val correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)
        val wrongAnswers = intent.getIntExtra("WRONG_ANSWERS", 0)

        // Visa antal rätt och fel svar
        val accuracy = (correctAnswers.toDouble() / (correctAnswers + wrongAnswers)) * 100
        val resultMessage = "Du fick $correctAnswers av ${correctAnswers + wrongAnswers} rätt. $accuracy% korrekt."

        val resultView = findViewById<TextView>(R.id.finalResult)
        resultView.text = resultMessage

        val restartButton = findViewById<Button>(R.id.restartButton)
        restartButton.setOnClickListener {
            finish() // Stäng ResultActivity istället för att starta om spelet
        }
    }
}