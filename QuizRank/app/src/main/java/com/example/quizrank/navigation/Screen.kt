package com.example.quizrank.navigation

import android.util.Log
import com.example.quizrank.ui.model.TopicUi

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Register: Screen("register")
    object Main: Screen("main")
    object Topics: Screen("topics")
    object Questions: Screen("topic/{id}-{title}-{userId}"){
        fun passData(id: String, title: String, userId: String): String {
            return "topic/$id-$title-$userId"
        }
    }
    object Results: Screen("result")
}
