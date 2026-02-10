package org.blindkey.data.local

import androidx.room.RoomRawQuery
import org.blindkey.data.local.model.TextEntity
import org.blindkey.data.res.NumberRes
import org.blindkey.domain.model.TestParam
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

    suspend fun getRandomText(testParam: TestParam): Text? {
        val random = Random.nextDouble(
            NumberRes.RANDOM_NUMBERS_RANGE_LOWER,
            NumberRes.RANDOM_NUMBERS_RANGE_UPPER
        )

        listOf("<=", ">").forEach { sign ->
            var index = 1
            val query = RoomRawQuery(getRandomSqlQuery(sign, testParam)) {

                it.bindDouble(index++, random)

                testParam.forEachNotNull { _, value ->
                    when (value) {
                        is Int -> { it.bindInt(index++, value) }
                        is String -> { it.bindText(index++, value) }
                        is IntRange -> {
                            it.bindInt(index++, value.first)
                            it.bindInt(index++, value.last)
                        }
                    }
                }
            }
            val text = try {
                dao.getRandomText(query)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            if (text != null) {
                return text.toText()
            }
        }
        return null
    }

    private fun getRandomSqlQuery(sign: String, testParam: TestParam): String {
        require(sign == "<=" || sign == ">") { "Invalid sign" }

        val endExpr = StringBuilder()

        testParam.forEachNotNull { param, _ ->
            if (param == TestParam.LENGTH_NAME) {
                endExpr.append(" AND $param BETWEEN ? AND ?")
            } else {
                endExpr.append(" AND $param = ?")
            }
        }
        val orderType = if (sign == "<=") "DESC" else "ASC"
        return "SELECT * FROM texts WHERE random $sign ?$endExpr ORDER BY random $orderType LIMIT 1"
    }
}