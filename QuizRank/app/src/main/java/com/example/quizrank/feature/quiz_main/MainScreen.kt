package com.example.quizrank.feature.quiz_main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizrank.R
import com.example.quizrank.ui.common.ButtonWithIcon
import com.example.quizrank.ui.common.QuizRankAppBar
import com.example.quizrank.ui.model.toUiText
import com.example.quizrank.R.string as StringResources

@ExperimentalMaterial3Api
@Composable
fun MainScreen(
    onSignOut: () -> Unit,
    onPlayClick: () -> Unit,
    viewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
){

    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            QuizRankAppBar(
                title = stringResource(id = StringResources.home_page),
                actions = {
                    IconButton(onClick = { /*TODO: LeaderBoard*/ }) {
                        Icon(imageVector = Icons.Default.Leaderboard, contentDescription = null)
                    }
                    IconButton(onClick = {
                        viewModel.signOut()
                        onSignOut()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    color = if (!state.isLoading && !state.isError) {
                        MaterialTheme.colorScheme.secondaryContainer
                    } else {
                        MaterialTheme.colorScheme.background
                    }
                ),
            contentAlignment = Alignment.Center
        )
        {
            if (state.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
            } else if (state.isError) {
                Text(
                    text = state.error?.toUiText()?.asString(context)
                        ?: stringResource(id = R.string.some_error_message)
                )
            } else{
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Welcome Player!",
                        modifier = Modifier.padding(bottom = 10.dp),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "In this app you can test your knowledge in available topics." +
                            "Before you start the game, please choose one topic!",
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center
                    )
                    ButtonWithIcon(
                        onClick = onPlayClick,
                        icon = Icons.Default.ManageSearch,
                        text = "Check the topics"
                    )
                }

            }
        }
    }
}