package com.example.quizrank.data.results.firebase

import com.example.quizrank.QuizRankApplication.Companion.authService
import com.example.quizrank.data.results.ResultService
import com.example.quizrank.domain.model.Result
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class FirebaseResultService(
    private val fireStore: FirebaseFirestore
): ResultService {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val results: Flow<List<Result>> = authService.currentUser.flatMapLatest { user ->
        if(user?.id == null) flow {emit(emptyList())}
        else currentCollection()
            .snapshots()
            .map { snapshot ->
                snapshot
                    .toObjects<FirebaseResult>()
                    .map{ it.asResult() }
            }

    }

    override suspend fun saveResult(result: Result){
        authService.currentUserId?.let {
            currentCollection().add(result.asFirebaseResult()).await()
        }
    }

    override suspend fun deleteResults(ids: List<String>) {
        ids.forEach { id ->
            authService.currentUserId?.let {
                currentCollection().document(id).delete().await()
            }
        }
    }

    private fun currentCollection() =
        fireStore.collection(RESULT_COLLECTION)

    companion object {
        private const val RESULT_COLLECTION = "results"
    }
}