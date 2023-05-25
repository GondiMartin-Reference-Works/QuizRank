package com.example.quizrank.data.topics.firebase

import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.data.topics.TopicService
import com.example.quizrank.domain.model.Topic
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FirebaseTopicService(
    private val firestore: FirebaseFirestore,
    private val authService: AuthService
) : TopicService {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val topics: Flow<List<Topic>> = authService.currentUser.flatMapLatest { user ->
        if (user == null) flow { emit(emptyList()) }
        else currentCollection()
            .snapshots()
            .map { snapshot ->
                snapshot
                    .toObjects<FirebaseTopic>()
                    .map {
                        it.asTopic()
                    }
            }
    }

    private fun currentCollection() =
        firestore.collection(TOPIC_COLLECTION)

    companion object {
        private const val TOPIC_COLLECTION = "temp-topics"
    }
}