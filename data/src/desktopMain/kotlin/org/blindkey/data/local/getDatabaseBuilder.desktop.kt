package org.blindkey.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import org.blindkey.data.getLocalFilesDir
import org.blindkey.data.res.RoomRes
import java.io.File

actual fun getDatabaseBuilder(): RoomDatabase.Builder<TextsDatabase> {
    val dbFile = File(getLocalFilesDir(), RoomRes.DATABASE_NAME)
    return Room.databaseBuilder<TextsDatabase>(
        name = dbFile.absolutePath,
    )
}