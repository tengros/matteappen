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

        // Anropa setNewQuestions för att börja med att skapa 10 frågor
        setNewQuestions(10) {
            if (operation == "PLUS") {
                setNewQuestionPlus()
            } else if (operation == "MINUS") {
                setNewQuestionMinus()
            } else if (operation == "MULTIPLIK") {
                setNewQuestionMultiplik()
            } else if (operation == "DIVISION") {
                setNewQuestionDivision()
            }
        }

        val backButton = findViewById<Button>(R.id.backButton2)

        backButton.setOnClickListener {
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                answeredQuestions = data?.getIntExtra("answeredQuestions", 0) ?: 0
                // Uppdatera värdet på answeredQuestions
            }
        }
    }


    override fun onResume() {
        super.onResume()
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

        answerView.text.clear()
    }

    fun setNewQuestions(total: Int, questionGenerator: () -> Unit) {
        totalQuestions = total
        correctAnswers = 0
        generateQuestion(questionGenerator)
    }

    fun generateQuestion(questionGenerator: () -> Unit) {
        if (totalQuestions > 0) {
            questionGenerator.invoke()
            totalQuestions--
            Log.d("!!!", "Genererade fråga, antal frågor kvar: $totalQuestions")
        } else {
            showResult()
            Log.d("!!!", "Visar resultat")
        }
    }

    fun showResult() {
        if (answeredQuestions >= 10) {
            val resultIntent = Intent(this, ResultActivity::class.java)
            resultIntent.putExtra("CORRECT_ANSWERS", correctAnswers)
            startActivity(resultIntent)
            finish()
        } else {
            // Annars visa antalet rätt svar
            val accuracy = (correctAnswers.toDouble() / 10) * 100
            val resultMessage = "Du fick $correctAnswers av 10 rätt. $accuracy% korrekt."
            // Visa resultatet på valfritt sätt, t.ex. genom att använda en Toast eller en AlertDialog
            // Exempel: Toast.makeText(this, resultMessage, Toast.LENGTH_LONG).show()
        }
    }

    fun checkAnswer(): Boolean {
        // Kolla vad användaren svarat
        val answerText = answerView.text.toString()
        val answer = answerText.toIntOrNull()

        // Jämför användarens svar med rätta svaret
        return answer == correctAnswer
    }

    fun handleAnswer() {
        val answeredCorrect = checkAnswer()
        answeredQuestions++

        if (answeredQuestions <= 10) {
            if (answeredQuestions == 10) {
                showResult()
            } else {
                val intent = Intent(this, AnswerActivity::class.java)
                intent.putExtra("answeredCorrect", answeredCorrect)
                intent.putExtra("answeredQuestions", answeredQuestions)
                startActivityForResult(intent, REQUEST_CODE)
            }
        }
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
