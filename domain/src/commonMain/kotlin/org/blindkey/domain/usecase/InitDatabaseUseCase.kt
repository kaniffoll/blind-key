package org.blindkey.domain.usecase

import org.blindkey.domain.repo.TextRepository

class InitDatabaseUseCase(private val textRepo: TextRepository)  {
    suspend operator fun invoke(forceInit: Boolean = false) { textRepo.initDatabase(forceInit) }
}