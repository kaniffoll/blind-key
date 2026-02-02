package org.blindkey.domain.repo

import org.blindkey.domain.model.Text

interface TextRepository {
    suspend fun initDatabase(forceInit: Boolean = false)
    suspend fun getRandomText(): Text
}