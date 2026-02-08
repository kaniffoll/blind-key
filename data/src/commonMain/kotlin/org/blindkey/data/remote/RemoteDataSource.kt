package org.blindkey.data.remote

import dev.gitlive.firebase.firestore.FirebaseFirestore
import org.blindkey.data.res.RemoteRes

class RemoteDataSource(private val db: FirebaseFirestore) {

    suspend fun getAllTexts(): List<TextDto> {
        val snapshot = db.collection(RemoteRes.COLLECTION_NAME).get()
        return snapshot.documents.map { doc ->
            doc.data(FireStoreResponse.serializer()).toTextDto(doc.id)
        }
    }

    suspend fun addNewText(text: HashMap<String, Any>) {
        db.collection(RemoteRes.COLLECTION_NAME)
            .add(text)
    }
}