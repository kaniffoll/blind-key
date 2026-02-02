package org.blindkey.data.local

import androidx.room.AutoMigration
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.blindkey.data.res.RoomRes

@Database(
    entities = [TextEntity::class],
    version = RoomRes.DATABASE_VERSION,
)
@ConstructedBy(TextsDatabaseConstructor::class)
abstract class TextsDatabase: RoomDatabase() {
    abstract val dao: TextDao
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object TextsDatabaseConstructor: RoomDatabaseConstructor<TextsDatabase> {
    override fun initialize(): TextsDatabase
}