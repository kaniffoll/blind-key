package org.blindkey.domain.repo

import org.blindkey.domain.model.Text

interface TextRepository {
    suspend fun getRandomText(): Text
}