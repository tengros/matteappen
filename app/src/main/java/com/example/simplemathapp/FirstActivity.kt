package com.example.simplemathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val buttonPlus = findViewById<Button>(R.id.plusButton)
        val buttonMinus = findViewById<Button>(R.id.minusButton)
        val buttonMultiplik = findViewById<Button>(R.id.multiplikButton)
        val buttonDivision = findViewById<Button>(R.id.divisionButton)

        buttonPlus.setOnClickListener {
            navigateToMainActivity("PLUS")
        }

        buttonMinus.setOnClickListener {
            navigateToMainActivity("MINUS")
        }

        buttonMultiplik.setOnClickListener {
            navigateToMainActivity("MULTIPLIK")
        }

        buttonDivision.setOnClickListener {
            navigateToMainActivity("DIVISION")
        }
    }

    private fun navigateToMainActivity(operation: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("OPERATION", operation)
        startActivity(intent)
    }
}