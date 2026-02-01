package org.blindkey.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import org.blindkey.data.res.FileSystemRes
import org.blindkey.data.res.RoomRes
import java.io.File

private fun databaseDir(): File {
    val os = System.getProperty("os.name").lowercase()
    val home = System.getProperty("user.home")
    val dir = when {
        "mac" in os -> File(home, FileSystemRes.MAC_OS_CHILD_NAME)
        "win" in os -> File(System.getenv(FileSystemRes.WINDOWS_OS_APP_DATA), FileSystemRes.APP_NAME)
        else -> File(home, FileSystemRes.LINUX_DIR_NAME)
    }
    dir.mkdirs()
    return dir
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<TextsDatabase> {
    val dbFile = File(databaseDir(), RoomRes.DATABASE_NAME)
    return Room.databaseBuilder<TextsDatabase>(
        name = dbFile.absolutePath,
    )
}