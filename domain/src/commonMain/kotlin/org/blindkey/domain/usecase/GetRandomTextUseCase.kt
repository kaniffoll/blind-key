package org.blindkey.domain.usecase

import org.blindkey.domain.repo.TextRepository

class GetRandomTextUseCase(private val textRepo: TextRepository) {
    suspend operator fun invoke() = textRepo.getRandomText()
}