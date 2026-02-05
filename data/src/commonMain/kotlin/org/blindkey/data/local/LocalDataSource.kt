package org.blindkey.data.local

import androidx.room.RoomRawQuery
import org.blindkey.data.res.NumberRes
import org.blindkey.domain.model.Text
import kotlin.random.Random

//TODO: e
class LocalDataSource(private val dao: TextDao) {

    suspend fun textsCount() = try {
        dao.count()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    suspend fun saveTexts(list: List<TextEntity>) {
        try {
            dao.insert(list)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getRandomText(): Text? {
        val random = Random.Default.nextDouble(
            NumberRes.RANDOM_NUMBERS_RANGE_LOWER,
            NumberRes.RANDOM_NUMBERS_RANGE_UPPER
        )
        listOf("<=", ">").forEach { sign ->
            val query = RoomRawQuery(getRandomSqlQuery(sign)) {
                it.bindDouble(1, random)
            }
            val text = try { dao.getRandomText(query) } catch (e: Exception) { null }
            if (text != null) { return text.toText() }
        }
        return null
    }

    private fun getRandomSqlQuery(sign: String): String {
        val orderType = if (sign == "<=") "DESC" else "ASC"
        return "SELECT * FROM texts WHERE random $sign ? ORDER BY random $orderType LIMIT 1"
    }
}