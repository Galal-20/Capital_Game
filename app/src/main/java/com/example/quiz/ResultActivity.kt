package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.properties.Delegates

class ResultActivity : AppCompatActivity() {
    private lateinit var tv_name : TextView
    private lateinit var tv_score : TextView
    private lateinit var tv_store : TextView
    private lateinit var finish: Button
    private lateinit var store: Button
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val sharedPreferences = getSharedPreferences("QuizPrefs", MODE_PRIVATE)



        tv_name = findViewById(R.id.tv_name)
        tv_score = findViewById(R.id.tv_score)
        finish = findViewById(R.id.btn_finish)
        store = findViewById(R.id.btnStore)
        tv_store = findViewById(R.id.tvStore)

        val username = intent.getStringExtra(Constants.USER_NAME)
        val total = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSWER, 0)
        tv_name.text = username
        tv_score.text = "Your Score is $correctAnswer from $total"


        val name = sharedPreferences.getString("name", "")
        val score = sharedPreferences.getInt("score", 0)

        store.setOnClickListener{
            tv_store.append("$name, you have score equal $score")
        }

        finish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}