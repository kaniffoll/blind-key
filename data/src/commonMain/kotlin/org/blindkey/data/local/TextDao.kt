package org.blindkey.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TextDao {
    @Query("SELECT * FROM texts WHERE random = :random LIMIT 1")
    suspend fun getRandomText(random: Double): TextEntity

    @Upsert
    suspend fun insert(texts: List<TextEntity>)
}