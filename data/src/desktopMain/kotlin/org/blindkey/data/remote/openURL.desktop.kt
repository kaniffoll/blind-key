package org.blindkey.data.remote

import java.awt.Desktop
import java.net.URI

actual fun openURL(url: String) {
    val desktop = Desktop.getDesktop()
    desktop.browse(URI.create(url))
}