package org.blindkey.app.model

data class TestResult(
    val wpm: Int,
    val accuracy: Int,
    val errorList: List<Int>
)
