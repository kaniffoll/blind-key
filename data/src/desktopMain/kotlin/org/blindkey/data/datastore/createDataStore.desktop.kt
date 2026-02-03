package org.blindkey.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.blindkey.data.getLocalFilesDir
import java.io.File

//fun createDataStore(): DataStore<Preferences> = createDataStore(
//    producePath = {
//        val file =
//        file.absolutePath
//    }
//)

actual fun createDataStore(): DataStore<Preferences> {
    val file = File(getLocalFilesDir(), dataStoreFileName).absolutePath
    return PreferenceDataStoreFactory.createWithPath(
        produceFile = { file.toPath() }
    )
}