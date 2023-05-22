package com.example.quizrank.data.topics.firebase

import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.data.topics.TopicService
import com.example.quizrank.domain.model.Topic
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
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

    override suspend fun getTopic(id: String): Topic? {
        val let = authService.currentUserId?.let {
            currentCollection().document(id).get().await().toObject<FirebaseTopic>()?.asTopic()
        }
        return let
    }

    private fun currentCollection() =
        firestore.collection(TOPIC_COLLECTION)

    companion object {
        private const val TOPIC_COLLECTION = "topics"
    }
}