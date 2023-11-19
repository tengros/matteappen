package com.example.simplemathapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var questionView: TextView
    lateinit var answerView: EditText
    var correctAnswer: Int = 0
    var totalQuestions: Int = 0
    var correctAnswers: Int = 0
    var wrongAnswers: Int = 0
    var answeredQuestions: Int = 0
    val REQUEST_CODE = 100


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionView = findViewById(R.id.questionView)
        answerView = findViewById(R.id.answerView)

        val button = findViewById<Button>(R.id.answerButton)

        button.setOnClickListener {
            handleAnswer()
        }

        val operation = intent.getStringExtra("OPERATION")


        if (operation == "PLUS") {
            setNewQuestionPlus()
        } else if (operation == "MINUS") {
            setNewQuestionMinus()
        } else if (operation == "MULTIPLIK") {
            setNewQuestionMultiplik()
        } else if (operation == "DIVISION") {
            setNewQuestionDivision()
        }


    val backButton = findViewById<Button>(R.id.backButton2)

    backButton.setOnClickListener {
        finish()
    }
}
    override fun onResume() {
        super.onResume()
        val operation = intent.getStringExtra("OPERATION")

        totalQuestions++
        if (operation == "PLUS") {
            setNewQuestionPlus()
        } else if (operation == "MINUS") {
            setNewQuestionMinus()
        } else if (operation == "MULTIPLIK") {
            setNewQuestionMultiplik()
        } else if (operation == "DIVISION") {
            setNewQuestionDivision()
        }


        Log.d("!!!", "Du har svarat på $answeredQuestions frågor rätt svar: $correctAnswers och fel svar $wrongAnswers")

        answerView.text.clear()
    }


    fun handleAnswer() {
        val answeredCorrect = checkAnswer()

        if (answeredCorrect) {
            correctAnswers++
        } else {
            wrongAnswers++
        }

        answeredQuestions++

        if (answeredQuestions < 10) {
            val intent = Intent(this, AnswerActivity::class.java)
            intent.putExtra("answeredCorrect", answeredCorrect)

        } else {
            val resultIntent = Intent(this, ResultActivity::class.java)
            startActivity(resultIntent)
        }

        Log.d("!!!", "Du har svarat på $answeredQuestions frågor. Rätt svar: $correctAnswers, fel svar: $wrongAnswers")
        answerView.text.clear()
    }

    fun checkAnswer(): Boolean {
        // Kolla vad användaren svarat
        val answerText = answerView.text.toString()
        val answer = answerText.toIntOrNull()

        // Jämför användarens svar med rätta svaret
        return answer == correctAnswer
    }

    fun setNewQuestionPlus() {
        val firstNumber = (1..10).random()
        val secondNumber = (1..10).random()

        correctAnswer = firstNumber + secondNumber
        questionView.text = "$firstNumber + $secondNumber ="

    }

    fun setNewQuestionMinus() {
        var firstNumber = (1..10).random()
        var secondNumber = (1..10).random()

        // Kontrollera så att det andra talet inte är större än det första
        while (secondNumber > firstNumber) {
            firstNumber = (1..10).random()
            secondNumber = (1..10).random()
        }
    }

        fun setNewQuestionMultiplik() {
            val firstNumber = (1..10).random()
            val secondNumber = (1..10).random()

            correctAnswer = firstNumber * secondNumber
            questionView.text = "$firstNumber * $secondNumber ="

        }

    fun setNewQuestionDivision() {
        val multiplier = (1..10).random()
        val firstNumber = multiplier * (1..10).random()
        val secondNumber = firstNumber / multiplier

        questionView.text = "$firstNumber / $multiplier ="
        correctAnswer = secondNumber
    }
    }
