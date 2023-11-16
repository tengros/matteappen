package com.example.simplemathapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.simplemathapp.Constants.REQUEST_CODE

class AnswerActivity : AppCompatActivity() {

    lateinit var resultView : TextView
    var answeredQuestions: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        resultView = findViewById(R.id.resultView)
        val nextQuestionsButton = findViewById<Button>(R.id.nextQuestionButton)

        nextQuestionsButton.setOnClickListener {
            goToNextQuestionOrEnd()
        }

        val answer = intent.getBooleanExtra("answeredCorrect", false)
        answeredQuestions = intent.getIntExtra("answeredQuestions", 0)

        if (answer) {
            resultView.text = "Rätt svar!"
        } else {
            resultView.text = "Fel svar!"
        }
    }

    private fun goToNextQuestionOrEnd() {
        if (shouldStartNewGame()) {
            val intent = Intent()
            intent.putExtra("answeredQuestions", answeredQuestions)
            setResult(Activity.RESULT_OK, intent) // Sätt resultatet och skicka tillbaka till MainActivity
            finish()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("OPERATION", getOperationFromUser())

            startActivityForResult(intent, REQUEST_CODE) // Använd REQUEST_CODE för att starta aktiviteten
            finish()
        }
    }

    private fun shouldStartNewGame(): Boolean {
        return answeredQuestions >= 10
    }

    private fun getOperationFromUser(): String {
        return "PLUS"
    }
}
