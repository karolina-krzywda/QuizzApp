package com.example.quizzapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzapp.databinding.ActivityQuizQuestioinBinding

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizQuestioinBinding

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question> = arrayListOf()
    private var mSelectesOption: Int = 0
    private var mUserName: String? = null
    private var mCorrectAnswear: Int = 0

    private val buttons = mutableListOf<Button>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizQuestioinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionList = Constants.getQuestions()

        with(binding) {
            optionOne.setOnClickListener(this@QuizQuestionActivity)
            optionTwo.setOnClickListener(this@QuizQuestionActivity)
            optionThree.setOnClickListener(this@QuizQuestionActivity)
            optionFour.setOnClickListener(this@QuizQuestionActivity)
            btnSubmit.setOnClickListener(this@QuizQuestionActivity)
            buttons.add(optionOne)
            buttons.add(optionTwo)
            buttons.add(optionThree)
            buttons.add(optionFour)
        }

        setQuestion()
    }

    private fun setQuestion() = with(binding) {
        defaultOptionView()

        val question: Question = mQuestionList[mCurrentPosition - 1]
        imageView2.setImageResource(question.image)
        progressBar.progress = mCurrentPosition
        progressText.text = "$mCurrentPosition / ${progressBar.max}"
        questionText.text = question.questions
        optionOne.text = question.optionOne
        optionTwo.text = question.optionTwo
        optionThree.text = question.optionThree
        optionFour.text = question.optionFour

        if (mCurrentPosition == mQuestionList.size) {
            btnSubmit.text = getString(R.string.finish)
        } else {
            btnSubmit.text = getString(R.string.subbmit)
        }
    }

    private fun defaultOptionView() {
        for (option in buttons) {
            option.setInitialOptionColor()
            option.setBackgroundColor(Color.BLUE)
        }
    }

    private fun Button.setInitialOptionColor() {
        setTextColor(Color.parseColor("#ffffff"))
    }

    private fun selectedOptionView(tv: Button, selectedOptionNum: Int) {
        defaultOptionView()
        mSelectesOption = selectedOptionNum
        tv.setTextColor((Color.parseColor("#FF0000")))
    }

    override fun onClick(view: View) = with(binding) {
        when (view.id) {
            R.id.optionOne -> selectedOptionView(optionOne, 1)
            R.id.optionTwo -> selectedOptionView(optionTwo, 2)
            R.id.optionThree -> selectedOptionView(optionThree, 3)
            R.id.optionFour -> selectedOptionView(optionFour, 4)
            R.id.btnSubmit -> {
                buttons.forEach { it.setInitialOptionColor() }

                if (mSelectesOption == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList.size -> {
                            setQuestion()
                        }

                        else -> {
                            val intent = Intent(this@QuizQuestionActivity, Finish::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)

                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswear)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionList.get(mCurrentPosition - 1)

                    if (question.correctAnswear != mSelectesOption) {
                        answerView(mSelectesOption, Color.RED)
                        answerView(question.correctAnswear, Color.GREEN)
                    } else {
                        mCorrectAnswear++
                        answerView(mSelectesOption, Color.GREEN)
                    }

                    if (mCurrentPosition == mQuestionList.size) {
                        btnSubmit.text = getString(R.string.finish)
                    } else {
                        btnSubmit.text = getString(R.string.go_to_next_quewstion)
                    }

                    mSelectesOption = 0
                }
            }
        }
    }

    private fun answerView(answear: Int, color: Int) = with(binding) {
        when (answear) {
            1 -> optionOne.setBackgroundColor(color)
            2 -> optionTwo.setBackgroundColor(color)
            3 -> optionThree.setBackgroundColor(color)
            4 -> optionFour.setBackgroundColor(color)
        }
    }
}
