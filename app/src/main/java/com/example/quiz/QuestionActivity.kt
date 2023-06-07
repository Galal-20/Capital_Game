package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.media.tv.TvView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var progressBar:ProgressBar
    private lateinit var submit : Button
    private lateinit var tvProgress: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var tvOne: TextView
    private lateinit var tvTwo: TextView
    private lateinit var tvThree: TextView
    private lateinit var tvFour: TextView
    private lateinit var ivImage: ImageView

    private var  mCurrentPosition:Int = 1
    private var mQuestionList : ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswer : Int = 0
    private var mUsername :String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        sharedPreferences = getSharedPreferences("QuizPrefs", MODE_PRIVATE)


        mUsername = intent.getStringExtra(Constants.USER_NAME)
        binding()


        mQuestionList = Constants.getquestions()
        setQuestion()

        tvOne.setOnClickListener (this)
        tvTwo.setOnClickListener (this)
        tvThree.setOnClickListener (this)
        tvFour.setOnClickListener (this)
        submit.setOnClickListener(this)




    }

    private fun binding(){
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.image_iv)
        tvOne = findViewById(R.id.tv_option_one)
        tvTwo = findViewById(R.id.tv_option_two)
        tvThree = findViewById(R.id.tv_option_three)
        tvFour = findViewById(R.id.tv_option_four)
        submit = findViewById(R.id.submit)
    }
    @SuppressLint("SetTextI18n")
    private fun setQuestion(){
        val question = mQuestionList!![mCurrentPosition-1]

        defaultOptionView()
        if (mCurrentPosition == mQuestionList!!.size){
            submit.text = "Finish"
        }else{
            submit.text = "Submit"
        }
        progressBar.progress = mCurrentPosition
        tvProgress.text = "$mCurrentPosition" + "/" + progressBar.max

        tvQuestion.text = question.question
        ivImage.setImageResource(question.image)
        tvOne.text = question.optionOne
        tvTwo.text = question.optionTwo
        tvThree.text = question.optionThree
        tvFour.text = question.optionFour
    }

    private fun defaultOptionView(){
        val options =  ArrayList<TextView>()
        options.add(0,tvOne)
        options.add(1,tvTwo)
        options.add(2,tvThree)
        options.add(3,tvFour)
        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one -> {selectOptionView(tvOne,1)}
            R.id.tv_option_two -> {selectOptionView(tvTwo,2)}
            R.id.tv_option_three -> {selectOptionView(tvThree,3)}
            R.id.tv_option_four -> {selectOptionView(tvFour,4)}
            R.id.submit -> {
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{mCurrentPosition <= mQuestionList!!.size ->{
                        setQuestion()
                    }else -> {
                        Intent(this, ResultActivity::class.java).also {
                            it.putExtra(Constants.USER_NAME, mUsername)
                            it.putExtra(Constants.CORRECT_ANSWER, mCorrectAnswer)
                            it.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                            startActivity(it)
                            finish()

                            // Save name and score to SharedPreferences
                            val editor = sharedPreferences.edit()
                            editor.putString("name", mUsername)
                            editor.putInt("score", mCorrectAnswer)
                            editor.apply()
                        }
                    }
                    }
                }else {
                    val question = mQuestionList?.get(mCurrentPosition -1)
                    if (question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionList!!.size){
                        submit.text = "Finish"
                    }else{
                        submit.text = "Click Next"
                    }
                    mSelectedOptionPosition = 0
                }

            }
        }

    }
    private fun answerView(answer:Int, drawableView:Int){
        when(answer){
            1 ->{tvOne.background = ContextCompat.getDrawable(this,drawableView)}
            2 ->{tvTwo.background = ContextCompat.getDrawable(this,drawableView)}
            3 ->{tvThree.background = ContextCompat.getDrawable(this,drawableView)}
            4 ->{tvFour.background = ContextCompat.getDrawable(this,drawableView)}
        }
    }
    private fun selectOptionView(textView: TextView, selectedOptionNum:Int){
        defaultOptionView()
        mSelectedOptionPosition= selectedOptionNum
        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }
}