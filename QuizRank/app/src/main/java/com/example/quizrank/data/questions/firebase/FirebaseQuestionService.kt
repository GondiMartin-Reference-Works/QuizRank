package com.example.quizrank.data.questions.firebase

import com.example.quizrank.QuizRankApplication.Companion.authService
import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.data.questions.QuestionService
import com.example.quizrank.data.topics.TopicService
import com.example.quizrank.domain.model.Question
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class FirebaseQuestionService(
    private val firestore: FirebaseFirestore
): QuestionService {

    private var _topicId: String = ""

    @OptIn(ExperimentalCoroutinesApi::class)
    override val questions: Flow<List<Question>> = authService.currentUser.flatMapLatest { user ->
        if (_topicId === "") flow {emit(emptyList())}
        else currentCollection(_topicId)
            .snapshots()
            .map { snapshot ->
                snapshot.toObjects<FirebaseQuestion>()
                    .map{ it.asQuestion() }
            }
    }

    override fun setTopicId(topicId: String){
        _topicId = topicId
    }
    private fun currentCollection(id: String) =
        firestore.collection(TOPIC_COLLECTION).document(id).collection(QUESTION_COLLECTION)

    companion object{
        private const val TOPIC_COLLECTION = "temp-topics"
        private const val QUESTION_COLLECTION = "questions"
    }
}
