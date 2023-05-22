package com.example.quizrank.data.topics.firebase

import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.data.topics.TopicService
import com.example.quizrank.domain.model.Topic
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await

class FirebaseTopicService(
    private val firestore: FirebaseFirestore,
    private val authService: AuthService
) : TopicService {

    override val topics: Flow<List<Topic>> = authService.currentUser.flatMapLatest { user ->
        if (user == null) flow { emit(emptyList()) }
        else currentCollection(user.id)
            .snapshots()
            .map { snapshot ->
                snapshot
                    .toObjects<FirebaseTopic>()
                    .map {
                        it.asTopic()
                    }
            }
    }

    override suspend fun getTopic(id: String): Topic? =
        authService.currentUserId?.let {
            currentCollection(it).document(id).get().await().toObject<FirebaseTopic>()?.asTopic()
        }

    override suspend fun saveTopic(topic: Topic) {
        authService.currentUserId?.let {
            currentCollection(it).add(topic.asFirebaseTopic()).await()
        }
    }

    override suspend fun updateTopic(topic: Topic) {
        authService.currentUserId?.let {
            currentCollection(it).document(topic.id).set(topic.asFirebaseTopic()).await()
        }
    }

    override suspend fun deleteTopic(id: String) {
        authService.currentUserId?.let {
            currentCollection(it).document(id).delete().await()
        }
    }

    private fun currentCollection(userId: String) =
        firestore.collection(USER_COLLECTION).document(userId).collection(TOPIC_COLLECTION)

    companion object {
        private const val USER_COLLECTION = "users"
        private const val TOPIC_COLLECTION = "topics"
    }
}