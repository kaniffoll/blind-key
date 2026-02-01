package org.blindkey.data.local

import androidx.room.RoomDatabase

expect fun getDatabaseBuilder(): RoomDatabase.Builder<TextsDatabase>