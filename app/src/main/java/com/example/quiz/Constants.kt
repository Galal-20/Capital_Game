package com.example.quiz

object Constants{

    const val USER_NAME:String = "user_name"
    const val TOTAL_QUESTIONS:String = "total_question"
    const val CORRECT_ANSWER:String = "correct_answer"

    fun getquestions(): ArrayList<Question>{
        val QuestionList = ArrayList<Question>()
        val que1 = Question(
            1,
            "What is the Capital of country?",R.drawable.egypt,
            "Cairo",
            "paris",
            "Yaman",
            "Giza",
            1
        )
        QuestionList.add(que1)

        val que2 = Question(
            2,
            "What is the Capital of country?",R.drawable.france,
            "Cairo",
            "paris",
            "Yaman",
            "Giza",
            2
        )
        QuestionList.add(que2)

        val que3 = Question(
            3,
            "What is the Capital of country?",R.drawable.us,
            "Cairo",
            "paris",
            "Yaman",
            "WS",
            4
        )
        QuestionList.add(que3)

        val que4 = Question(
            4,
            "What is the Capital of country?",R.drawable.london,
            "Cairo",
            "paris",
            "london",
            "Giza",
            3
        )
        QuestionList.add(que4)

        return QuestionList
    }
}