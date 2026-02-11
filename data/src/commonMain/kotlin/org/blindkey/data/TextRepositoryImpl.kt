package org.blindkey.data

import org.blindkey.data.local.LocalDataSource
import org.blindkey.data.remote.RemoteDataSource
import org.blindkey.data.remote.toEntity
import org.blindkey.domain.model.Text
import org.blindkey.domain.repo.TextRepository
import androidx.room.concurrent.AtomicBoolean
import org.blindkey.domain.model.TestParam

class TextRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : TextRepository {

    private val isInitialized = AtomicBoolean(false)

    private suspend fun initDatabase() {
        if (localDataSource.textsCount() == 0) {
            localDataSource.saveTexts(remoteDataSource.getAllTexts().map { it.toEntity() })
            isInitialized.set(true)
        }
    }

    override suspend fun getRandomText(testParam: TestParam): Text {
        if (!isInitialized.get()) {
            initDatabase()
        }
        return localDataSource.getRandomText(testParam) ?: Text("NO CONTENT", wordsCount = 2)
    }

    override suspend fun addText(text: HashMap<String, Any>) {
        remoteDataSource.addNewText(text)
    }
}