package org.blindkey.domain.mappers

import org.blindkey.domain.model.ThemeParam

fun String.toThemeParam(): ThemeParam {
    return when(this) {
        "light" -> {
            ThemeParam.Light
        }
        "dark" -> {
            ThemeParam.Dark
        }
        "system" -> {
            ThemeParam.System
        }
        else -> throw IllegalArgumentException("Unknown param: $this")
    }
}