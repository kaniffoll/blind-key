package org.blindkey.domain.repo

import org.blindkey.domain.model.TestParam
import org.blindkey.domain.model.Text

interface TextRepository {
    suspend fun initDatabase(forceInit: Boolean = false)
    suspend fun getRandomText(testParam: TestParam): Text

    suspend fun addText(text: HashMap<String, Any>)
}