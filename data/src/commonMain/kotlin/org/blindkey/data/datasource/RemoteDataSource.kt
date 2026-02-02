package org.blindkey.data.datasource

import dev.gitlive.firebase.firestore.Filter
import dev.gitlive.firebase.firestore.FilterBuilder
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.QuerySnapshot
import org.blindkey.data.remote.FireStoreResponse
import org.blindkey.data.remote.TextDto
import org.blindkey.data.remote.toText
import org.blindkey.data.remote.toTextDto
import org.blindkey.data.res.RemoteRes
import org.blindkey.data.res.NumberRes.RANDOM_NUMBERS_RANGE_LOWER
import org.blindkey.data.res.NumberRes.RANDOM_NUMBERS_RANGE_UPPER
import org.blindkey.domain.model.Text
import kotlin.random.Random

class RemoteDataSource(private val db: FirebaseFirestore) {

    suspend fun getAllTexts(): List<TextDto> {
        val snapshot = db.collection(RemoteRes.COLLECTION_NAME).get()
        return snapshot.documents.map { doc ->
            doc.data(FireStoreResponse.serializer()).toTextDto(doc.id)
        }
    }

    suspend fun getRandomText(): Text {
        val random = Random.nextDouble(RANDOM_NUMBERS_RANGE_LOWER, RANDOM_NUMBERS_RANGE_UPPER)
        val snapshot = getRandomSnapshot { RemoteRes.RANDOM_FIELD_NAME lessThanOrEqualTo random }
        if (snapshot.documents.isNotEmpty()) {
            return snapshot.documents[0].data(FireStoreResponse.serializer()).toText()
        }
        return getRandomSnapshot { RemoteRes.RANDOM_FIELD_NAME greaterThan random }
            .documents[0]
            .data(FireStoreResponse.serializer())
            .toText()
    }

    private suspend fun getRandomSnapshot(builder: FilterBuilder.() -> Filter?): QuerySnapshot {
        return db.collection(RemoteRes.COLLECTION_NAME)
            .where(builder)
            .orderBy(RemoteRes.RANDOM_FIELD_NAME)
            .limit(1)
            .get()
    }
}