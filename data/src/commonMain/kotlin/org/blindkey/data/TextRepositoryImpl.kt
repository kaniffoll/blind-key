package org.blindkey.data

import org.blindkey.data.datasource.LocalDataSource
import org.blindkey.data.datasource.RemoteDataSource
import org.blindkey.data.remote.toEntity
import org.blindkey.domain.model.Text
import org.blindkey.domain.repo.TextRepository
import androidx.room.concurrent.AtomicBoolean

class TextRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : TextRepository {

    private val isInitialized = AtomicBoolean(false)

    override suspend fun initDatabase(forceInit: Boolean) {
        if (localDataSource.textsCount() == 0 || forceInit) {
            localDataSource.saveTexts(remoteDataSource.getAllTexts().map { it.toEntity() })
            isInitialized.set(true)
        }
    }

    override suspend fun getRandomText(): Text {
        if (!isInitialized.get()) {
            initDatabase()
        }
        //return remoteDataSource.getRandomText()
        return localDataSource.getRandomText() ?: Text("NO CONTENT", false, "en", 10)
    }
}