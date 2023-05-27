package com.example.quizrank.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultPage(
    onQuitClick: () -> Unit = { },
    onSaveClick: (text: String) -> Unit = { },
    questionsCount: Int = 50,
    goodAnswerCount: Int = 50
){
    var name by remember {mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 0.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Card(
            text = "Congrats!\n\nCheck your score below!\n\n\n" +
                    "${goodAnswerCount}/${questionsCount}"
        )
        TextField(
            value = name,
            onValueChange = { newText ->
                name = newText
            },
            label = { Text(text = "Name") },
            placeholder = { Text(text = "Type your name") }
        )
        Button(
            onClick = { onSaveClick(name); onQuitClick() },
            modifier = Modifier
                .fillMaxWidth(0.75f)
        ){
            Text(text = "Save your Result")
        }
    }
}

@Preview
@Composable
fun ResultPagePreview(){
    ResultPage()
}