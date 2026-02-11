package org.blindkey.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

//fun createDataStore(producePath: () -> String): DataStore<Preferences> =
//    PreferenceDataStoreFactory.createWithPath(
//        produceFile = { producePath().toPath() }
//    )

expect fun createDataStore(): DataStore<Preferences>

internal const val dataStoreFileName = "blindkey.preferences_pb"