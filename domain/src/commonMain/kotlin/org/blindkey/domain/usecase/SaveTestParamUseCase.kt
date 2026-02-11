package org.blindkey.domain.usecase

import org.blindkey.domain.model.TestParam
import org.blindkey.domain.settings.AppSettings

class SaveTestParamUseCase(private val settings: AppSettings) {
    suspend operator fun invoke(testParam: TestParam) = settings.saveTestParameters(testParam)
}