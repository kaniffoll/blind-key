package org.blindkey.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.RoomRawQuery
import androidx.room.Upsert

@Dao
interface TextDao {
//    @Query("SELECT * FROM texts WHERE random = :random LIMIT 1")
//    suspend fun getRandomText(random: Double): TextEntity?

    @RawQuery
    suspend fun getRandomText(query: RoomRawQuery): TextEntity?

    @Upsert
    suspend fun insert(texts: List<TextEntity>)

    @Query("SELECT count(*) FROM texts")
    suspend fun count(): Int
}