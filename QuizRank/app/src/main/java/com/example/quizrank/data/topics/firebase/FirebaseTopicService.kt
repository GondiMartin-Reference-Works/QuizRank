package com.example.quizrank.data.topics.firebase

import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.data.topics.TopicService
import com.example.quizrank.domain.model.Topic
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

class FirebaseTopicService(
    private val fireStore: FirebaseFirestore,
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
        fireStore.collection(TOPIC_COLLECTION)

    companion object {
        private const val TOPIC_COLLECTION = "topics"
    }
}