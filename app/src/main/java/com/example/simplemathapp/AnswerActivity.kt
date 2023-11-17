package com.example.simplemathapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.simplemathapp.Constants.REQUEST_CODE

class AnswerActivity : AppCompatActivity() {

    lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        resultView = findViewById(R.id.resultView)
        val nextQuestionsButton = findViewById<Button>(R.id.nextQuestionButton)

        val answer = intent.getBooleanExtra("answeredCorrect", false)
        val answeredQuestions = intent.getIntExtra("answeredQuestions", 0) + 1

        Log.d("...", "Du har svarat på $answeredQuestions frågor")

        if (answeredQuestions >= 10) {
            // Om antalet besvarade frågor är 10 eller fler, visa inte knappen för nästa fråga
            nextQuestionsButton.visibility = View.GONE
        } else {
            // Om antalet besvarade frågor är mindre än 10, visa knappen för nästa fråga och hantera klick
            nextQuestionsButton.setOnClickListener {
                // Skicka användaren till MainActivity för en ny fråga

                finish()
            }
        }

        // Visa "Rätt svar!" eller "Fel svar!" baserat på användarens svar
        if (answer) {
            resultView.text = "Rätt svar!"
        } else {
            resultView.text = "Fel svar!"
        }
    }

    override fun onResume() {
        super.onResume()


    }
}