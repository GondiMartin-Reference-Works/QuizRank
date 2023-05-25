package com.example.quizrank.ui.common

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.quizrank.R
import com.example.quizrank.feature.quiz_questions.QuestionsState
import com.example.quizrank.ui.model.QuestionUi
import com.example.quizrank.ui.model.toUiText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    context: Context,
    state: QuestionsState,
    onQuit: () -> Unit = { },
    onButtonClick: () -> Unit = { },
    currentQuestionIndex: Int = 0
){


}