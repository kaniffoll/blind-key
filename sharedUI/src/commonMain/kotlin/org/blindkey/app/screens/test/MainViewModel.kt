package org.blindkey.app.screens.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.blindkey.app.model.Key
import org.blindkey.app.model.TestResult
import org.blindkey.domain.model.TestParam
import org.blindkey.domain.usecase.AddTextUseCase
import org.blindkey.domain.usecase.GetRandomTextUseCase
import org.blindkey.domain.usecase.InitDatabaseUseCase
import kotlin.let

class MainViewModel(
    private val getRandomTextUseCase: GetRandomTextUseCase,
    private val initDatabaseUseCase: InitDatabaseUseCase,
    private val addTextUseCase: AddTextUseCase,
) : ViewModel() {
    //private val logger = Logger.withTag("TestViewModel")
    private var _currentText = MutableStateFlow("")
    val currentText = _currentText.asStateFlow()
    private var _isTestFinished = MutableStateFlow(false)
    val isTestFinished = _isTestFinished.asStateFlow()
    private var currentIndex = 0
    private var _typedKeyList = MutableStateFlow(emptyList<Key>())
    val typedKeyList = _typedKeyList.asStateFlow()
    val currentKey: Char?
        get() = if (currentIndex == _currentText.value.count()) {
            _isTestFinished.value = true
            endTest()
            null
        }
        else _currentText.value[currentIndex]
    private var testParam = TestParam()
    private var isErrForCurrentKey = false
    private lateinit var errList: MutableList<Int>
    private var startTime: Long? = null
    private var endTime: Long? = null
    private var totalTime: Long? = null

    init {
        viewModelScope.launch(Dispatchers.Default) {
            initDatabaseUseCase()
            getNewText()
        }
    }

    private fun endTest() {
        endTime = System.currentTimeMillis()
        totalTime = startTime?.let { sT -> endTime?.let { et -> et - sT } }
    }

    fun getTestResult(): TestResult = TestResult(totalTime, errList)

    fun changeHasPunctuation(hasPunctuationAsString: String) {
        testParam = testParam.copy(hasPunctuation = TestParam.getHasPunctuationByStringValue(hasPunctuationAsString))
        getNewText()
    }

    fun changeLanguage(languageAsString: String) {
        testParam = testParam.copy(language = TestParam.getLanguageByStringValue(languageAsString))
        getNewText()
    }

    fun changeLength(lengthAsString: String) {
        testParam = testParam.copy(length = TestParam.getLengthByStringValue(lengthAsString))
        getNewText()
    }

    fun resetTypedKeyList() {
        currentIndex = 0
        _typedKeyList.value = emptyList()
        startTime = null
        endTime = null
        totalTime = null
        errList = MutableList(_currentText.value.count()) { 0 }
    }

    fun getNewText() {
        viewModelScope.launch(Dispatchers.Default) {
            _isTestFinished.value = false
            _currentText.value = getRandomTextUseCase(testParam).content
            resetTypedKeyList()
        }
    }

    fun checkKey(key: Char) {
        if (startTime == null) {
            startTime = System.currentTimeMillis()
        }

        currentKey ?: return

        if (key == currentKey) {
            val newKey = if (isErrForCurrentKey) Key.ErrNoted(key) else Key.OkNoted(key)
            _typedKeyList.value = _typedKeyList.value.toMutableList().apply { add(newKey) }
            isErrForCurrentKey = false
            currentIndex += 1
            return
        }
        isErrForCurrentKey = true
        errList[currentIndex]++
    }

//    fun addText() {
//        viewModelScope.launch(Dispatchers.Default) {
//            val content = "While Tom was eating his supper, and stealing sugar as opportunity offered, Aunt Polly asked him questions that were full of guile, and very deep—for she wanted to trap him into damaging revealments."
//            addTextUseCase(
//                hashMapOf(
//                    "content" to content,
//                    "hasPunctuation" to true,
//                    "language" to "en",
//                    "length" to content.length,
//                    "random" to 4.934720
//                )
//            )
//        }
//    }
}