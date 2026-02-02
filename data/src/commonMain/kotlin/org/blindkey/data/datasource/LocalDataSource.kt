package org.blindkey.data.datasource

import androidx.room.RoomRawQuery
import org.blindkey.data.local.TextDao
import org.blindkey.data.local.TextEntity
import org.blindkey.data.local.toText
import org.blindkey.data.res.NumberRes.RANDOM_NUMBERS_RANGE_LOWER
import org.blindkey.data.res.NumberRes.RANDOM_NUMBERS_RANGE_UPPER
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
        val random = Random.nextDouble(RANDOM_NUMBERS_RANGE_LOWER, RANDOM_NUMBERS_RANGE_UPPER)
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