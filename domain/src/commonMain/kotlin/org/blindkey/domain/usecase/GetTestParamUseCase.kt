package org.blindkey.domain.usecase

import org.blindkey.domain.settings.AppSettings

class GetTestParamUseCase(private val settings: AppSettings) {
    suspend operator fun invoke() = settings.getTestParameters()
}