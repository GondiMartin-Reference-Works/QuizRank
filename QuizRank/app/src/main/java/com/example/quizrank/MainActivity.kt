package com.example.quizrank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.data.auth.FirebaseAuthService
import com.example.quizrank.navigation.NavGraph
import com.example.quizrank.ui.theme.QuizRankTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private val authService: AuthService by lazy {
        FirebaseAuthService(FirebaseAuth.getInstance())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizRankTheme {
                NavGraph(authService = authService)
            }
        }
    }
}