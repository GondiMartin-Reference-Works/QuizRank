package com.example.quizrank.feature.quiz_questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.quizrank.QuizRankApplication
import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.data.questions.QuestionService

class QuestionsViewModel constructor(
    private val authService: AuthService,
    private val questionService: QuestionService
) : ViewModel() {

    //TODO: Load questions

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val authService = QuizRankApplication.authService
                val questionService = QuizRankApplication.questionService
                QuestionsViewModel(
                    authService = authService,
                    questionService = questionService
                )
            }
        }
    }
}

//TODO QuestionsState