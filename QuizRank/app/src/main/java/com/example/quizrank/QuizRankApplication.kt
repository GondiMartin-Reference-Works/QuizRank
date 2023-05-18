package com.example.quizrank

import android.app.Application
import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.data.auth.FirebaseAuthService
import com.google.firebase.auth.FirebaseAuth

class QuizRankApplication : Application() {

    companion object {
        lateinit var authService: AuthService
    }

    override fun onCreate() {
        super.onCreate()
        authService = FirebaseAuthService(FirebaseAuth.getInstance())
    }
}
