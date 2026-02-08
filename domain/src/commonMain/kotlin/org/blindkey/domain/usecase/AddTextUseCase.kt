package org.blindkey.domain.usecase

import org.blindkey.domain.repo.TextRepository

class AddTextUseCase(private val repository: TextRepository) {
    suspend operator fun invoke(text: HashMap<String, Any>) { repository.addText(text) }
}