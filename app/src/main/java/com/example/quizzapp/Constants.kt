package com.example.quizzapp

object Constants {
    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answears"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val q1 = Question(
            1, "What country does this flag belong to?", R.drawable.ic_poland,
            "Germany", "Poland", "USA", "Maroco", 2
        )
        questionsList.add(q1)
        val q2 = Question(
            1, "What country does this flag belong to?", R.drawable.ic_turkey,
            "France", "Turkey", "Brazil", "New Zealand", 2
        )
        questionsList.add(q2)
        val q3 = Question(
            1, "What country does this flag belong to?", R.drawable.ic_usa,
            "USA", "Urugway", "Ukraine", "Maroco", 1
        )
        questionsList.add(q3)
        val q4 = Question(
            1, "What country does this flag belong to?", R.drawable.ic_canada,
            "Germany", "Poland", "Canada", "Maroco", 3
        )
        questionsList.add(q4)
        val q5 = Question(
            1, "What country does this flag belong to?", R.drawable.ic_italy,
            "Germany", "Poland", "Canada", "Italy", 4
        )
        questionsList.add(q5)
        val q6 = Question(
            1, "What country does this flag belong to?", R.drawable.ic_ukraine,
            "Ukraine", "Poland", "Canada", "Itlay", 1
        )
        questionsList.add(q6)

        return questionsList
    }


}