package utils

import android.app.Application
import co.touchlab.kermit.Logger
import com.google.firebase.FirebasePlatform
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize
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

    val options = FirebaseOptions(
        projectId = FireStoreRes.PROJECT_ID,
        applicationId = FireStoreRes.APP_ID,
        apiKey = FireStoreRes.API_KEY,
    )

    Firebase.initialize(Application(), options)
}