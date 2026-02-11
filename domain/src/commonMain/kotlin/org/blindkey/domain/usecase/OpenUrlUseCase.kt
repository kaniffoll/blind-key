package org.blindkey.domain.usecase

import org.blindkey.domain.uri.UrlOpener

class OpenUrlUseCase(private val urlOpener: UrlOpener) {
    operator fun invoke() = urlOpener.openGitHub()
}