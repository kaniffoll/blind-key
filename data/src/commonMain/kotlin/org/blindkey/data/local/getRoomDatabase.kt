package org.blindkey.data.local

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun getRoomDatabase(
    roomDatabaseBuilder: RoomDatabase.Builder<TextsDatabase>
): TextsDatabase {
    return roomDatabaseBuilder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}