package org.blindkey.app.model

data class TestResult(
    val totalTime: Long? = null,
    val errorList: List<Int>
)
