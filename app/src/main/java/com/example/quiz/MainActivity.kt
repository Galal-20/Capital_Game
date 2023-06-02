package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    private lateinit var btnStart :Button
    private lateinit var editName :AppCompatEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        fvb()

    }

    private fun fvb(){
        editName = findViewById(R.id.editName)
        btnStart = findViewById(R.id.btn_start)
    }

    fun start(view: View) {
        val name = editName.text.toString()
        if (name.isEmpty()){
            Toast.makeText(this, "Please Enter your name",Toast.LENGTH_SHORT).show()
        }else{
            Intent(this,QuestionActivity::class.java).also {
                it.putExtra(Constants.USER_NAME, name)
                startActivity(it)
                finish()
            }
        }
    }
}