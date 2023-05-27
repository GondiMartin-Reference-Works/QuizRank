package com.example.quizrank.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Card(
    text: String = "Put Question Here"
){
    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
            .fillMaxHeight(0.35f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = text,
            modifier = Modifier.padding(10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun CardPreview(){
    Card()
}