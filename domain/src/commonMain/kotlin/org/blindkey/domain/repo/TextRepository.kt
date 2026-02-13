package org.blindkey.domain.repo

import org.blindkey.domain.model.TestParam
import org.blindkey.domain.model.Text

interface TextRepository {
    suspend fun getRandomText(testParam: TestParam): Text?
}