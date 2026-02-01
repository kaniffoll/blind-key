package org.blindkey.data.datasource

import dev.gitlive.firebase.firestore.Filter
import dev.gitlive.firebase.firestore.FilterBuilder
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.QuerySnapshot
import org.blindkey.data.remote.TextDto
import org.blindkey.data.remote.toText
import org.blindkey.data.res.FireStoreRes
import org.blindkey.data.res.NumberRes.RANDOM_NUMBERS_RANGE_LOWER
import org.blindkey.data.res.NumberRes.RANDOM_NUMBERS_RANGE_UPPER
import org.blindkey.domain.model.Text
import kotlin.random.Random

class RemoteDataSource(private val db: FirebaseFirestore) {

    suspend fun getAllTexts(): List<Text> {
        val snapshot = db.collection(FireStoreRes.COLLECTION_NAME).get()
        return snapshot.documents.map { doc ->
            doc.data(TextDto.serializer()).toText()
        }
    }

    suspend fun getRandomText(): Text {
        val random = Random.nextDouble(RANDOM_NUMBERS_RANGE_LOWER, RANDOM_NUMBERS_RANGE_UPPER)
        val snapshot = getRandomSnapshot { FireStoreRes.RANDOM_FIELD_NAME lessThanOrEqualTo random }
        if (snapshot.documents.isNotEmpty()) {
            return snapshot.documents[0].data(TextDto.serializer()).toText()
        }
        return getRandomSnapshot { FireStoreRes.RANDOM_FIELD_NAME greaterThan random }
            .documents[0]
            .data(TextDto.serializer())
            .toText()
    }

    private suspend fun getRandomSnapshot(builder: FilterBuilder.() -> Filter?): QuerySnapshot {
        return db.collection(FireStoreRes.COLLECTION_NAME)
            .where(builder)
            .orderBy(FireStoreRes.RANDOM_FIELD_NAME)
            .limit(1)
            .get()
    }
}