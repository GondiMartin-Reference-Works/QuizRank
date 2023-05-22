package com.example.quizrank.data.questions.firebase

import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.data.questions.QuestionService
import com.example.quizrank.domain.model.Question
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class FirebaseQuestionService(
    private val firestore: FirebaseFirestore,
    private val authService: AuthService,
): QuestionService {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val questions: Flow<List<Question>> = authService.currentUser.flatMapLatest { user ->
        if (user == null) flow {emit(emptyList())}
        else currentCollection()
            .snapshots()
            .map { snapshot ->
                snapshot.toObjects<FirebaseQuestion>()
                    .map{ it.asQuestion() }
            }
    }

    override suspend fun getQuestion(id: String): Question? {
        val let = authService.currentUserId?.let {
            currentCollection().document(id).get().await().toObject<FirebaseQuestion>()?.asQuestion()
        }
        return let
    }

    private fun currentCollection() =
        firestore.collection(QUESTION_COLLECTION)

    companion object{
        private const val QUESTION_COLLECTION = "questions"
    }
}
