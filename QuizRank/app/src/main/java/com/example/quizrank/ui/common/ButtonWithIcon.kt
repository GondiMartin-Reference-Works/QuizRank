package com.example.quizrank.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ButtonWithIcon(
    icon : ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = null)
        Text(text = text,Modifier.padding(start = 10.dp))
    }
}