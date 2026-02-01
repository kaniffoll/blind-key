package org.blindkey.data

import org.blindkey.data.datasource.RemoteDataSource
import org.blindkey.data.res.SomeStringResources
import org.blindkey.domain.model.Text
import org.blindkey.domain.repo.TextRepository

class TextRepositoryImpl(private val remoteDataSource: RemoteDataSource): TextRepository {
    override suspend fun getRandomText(): Text {
        return remoteDataSource.getRandomText()
    }
}