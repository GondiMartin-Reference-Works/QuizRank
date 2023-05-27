package com.example.quizrank.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TwoOptionQuestion(
    text: String = "Put Question Here",
    onButtonClick: (text: String) -> Unit = { },
    firstButtonText: String = "A",
    secondButtonText: String = "B",
){
    Column(
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 0.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Card(
            text = text
        )
        TwoOptionButtons(
            onButtonClick = onButtonClick,
            firstButtonText = firstButtonText,
            secondButtonText = secondButtonText,
        )
    }
}

@Preview
@Composable
fun TwoOptionQuestionPreview(){
    TwoOptionQuestion()
}