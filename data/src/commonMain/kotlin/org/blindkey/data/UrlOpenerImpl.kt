package org.blindkey.data

import org.blindkey.data.remote.openURL
import org.blindkey.domain.uri.UrlOpener

class UrlOpenerImpl: UrlOpener {
    override fun openGitHub() = openURL(GITHUB_URL)

    companion object {
        const val GITHUB_URL = "https://github.com/kaniffoll/blind-key"
    }
}