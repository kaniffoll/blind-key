package org.blindkey.data

import org.blindkey.data.res.FileSystemRes
import java.io.File

internal fun getLocalFilesDir(): File {
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