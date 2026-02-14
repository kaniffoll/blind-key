package utils

import android.app.Application
import co.touchlab.kermit.Logger
import com.google.firebase.FirebaseOptions
import com.google.firebase.FirebasePlatform
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import res.FireStoreRes


fun initFireStore() {

    FirebasePlatform.initializeFirebasePlatform(object : FirebasePlatform() {

        val storage = mutableMapOf<String, String>()
        val logger = Logger.withTag("FireStore")

        override fun clear(key: String) {
            storage.remove(key)
        }

        override fun log(msg: String) = logger.i(msg)

        override fun retrieve(key: String) = storage[key]

        override fun store(key: String, value: String) = storage.set(key, value)

    })

    val options = FirebaseOptions.Builder()
        .setProjectId(FireStoreRes.PROJECT_ID)
        .setApplicationId(FireStoreRes.APP_ID)
        .setApiKey(FireStoreRes.API_KEY)
        .build()
//        projectId = FireStoreRes.PROJECT_ID,
//        applicationId = FireStoreRes.APP_ID,
//        apiKey = FireStoreRes.API_KEY,
//    )

    Firebase.initialize(
        context = Application(),
        options = options
    )
}