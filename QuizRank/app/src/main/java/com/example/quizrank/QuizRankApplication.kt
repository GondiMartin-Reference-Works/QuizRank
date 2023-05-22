package com.example.quizrank

import android.app.Application
import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.data.auth.FirebaseAuthService
import com.example.quizrank.data.topics.TopicService
import com.example.quizrank.data.topics.firebase.FirebaseTopicService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class QuizRankApplication : Application() {

    companion object {
        lateinit var authService: AuthService
        lateinit var topicService: TopicService
    }

    override fun onCreate() {
        super.onCreate()
        authService = FirebaseAuthService(FirebaseAuth.getInstance())
        topicService = FirebaseTopicService(FirebaseFirestore.getInstance(), authService)
    }
}
