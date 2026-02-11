package org.blindkey.app.screens.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.blindkey.app.model.Key
import org.blindkey.app.model.TestResult
import org.blindkey.domain.model.TestParam
import org.blindkey.domain.model.Text
import org.blindkey.domain.usecase.AddTextUseCase
import org.blindkey.domain.usecase.GetRandomTextUseCase
import org.blindkey.domain.usecase.GetTestParamUseCase
import org.blindkey.domain.usecase.SaveTestParamUseCase

class MainViewModel(
    private val getRandomTextUseCase: GetRandomTextUseCase,
    private val getTestParamUseCase: GetTestParamUseCase,
    private val saveTestParamUseCase: SaveTestParamUseCase,
    private val addTextUseCase: AddTextUseCase,
) : ViewModel() {
    //private val logger = Logger.withTag("TestViewModel")
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private var _currentText = MutableStateFlow(Text("", 0))
    val currentText = _currentText.asStateFlow()
    private var _isTestFinished = MutableStateFlow(false)
    val isTestFinished = _isTestFinished.asStateFlow()
    private var currentIndex = 0
    private var _typedKeyList = MutableStateFlow(emptyList<Key>())
    val typedKeyList = _typedKeyList.asStateFlow()
    val currentKey: Char
        get() = _currentText.value.content[currentIndex]
    private var _testParam = MutableStateFlow(TestParam())
    val testParam = _testParam.asStateFlow()
    private var isErrForCurrentKey = false
    private lateinit var errList: MutableList<Int>
    private var startTime: Long? = null
    private var totalTime: Long? = null

    private var _dumpText = MutableStateFlow("")
    val dumpText: StateFlow<String> = _dumpText.asStateFlow()

    fun onTextChange(text: String) {
        _dumpText.value = text
        checkKey(_dumpText.value.last())
    }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            getTestParam()
            getNewText()
            _isLoading.value = false
        }
    }

    private suspend fun getTestParam() {
        _testParam.value = getTestParamUseCase()
    }

    private fun endTest() {
        val endTime = System.currentTimeMillis()
        totalTime = startTime?.let { endTime - it }
    }

    fun getTestResult(): TestResult {
        val wpm = (_currentText.value.wordsCount / totalTimeAsMinutes()).toInt()
        val accuracy = getAccuracy()
        return TestResult(wpm, accuracy, errList)
    }

    fun changeTestParam(param: TestParam.Companion.Param) {
        _testParam.value = when (param) {
            is TestParam.Companion.Param.HasPunctuation ->
                _testParam.value.copy(hasPunctuation = TestParam.getHasPunctuationByStringValue(param.value))
            is TestParam.Companion.Param.Language ->
                _testParam.value.copy(language = TestParam.getLanguageByStringValue(param.value))
            is TestParam.Companion.Param.Length ->
                _testParam.value.copy(length = TestParam.getLengthByStringValue(param.value))
        }
        getNewText()
        viewModelScope.launch {
            saveTestParamUseCase(_testParam.value)
        }
    }

    fun reset() {
        currentIndex = 0
        _typedKeyList.value = emptyList()
        startTime = null
        totalTime = null
        _isTestFinished.value = false
        _dumpText.value = ""
        errList = MutableList(_currentText.value.content.count()) { 0 }
    }

    fun getNewText() {
        viewModelScope.launch(Dispatchers.Default) {
            _isTestFinished.value = false
            _currentText.value = getRandomTextUseCase(_testParam.value)
            reset()
        }
    }

    private fun checkKey(key: Char) {
        startTime ?: run { startTime = System.currentTimeMillis() }

        if (key == currentKey) {
            val newKey = if (isErrForCurrentKey) Key.ErrNoted(key) else Key.OkNoted(key)
            _typedKeyList.value = _typedKeyList.value.toMutableList().apply { add(newKey) }
            isErrForCurrentKey = false

            if (currentIndex == _currentText.value.content.count() - 1) {
                _isTestFinished.value = true
                endTest()
            }
            currentIndex += 1
            return
        }
        isErrForCurrentKey = true
        errList[currentIndex]++
    }

    private fun totalTimeAsMinutes(): Double = totalTime!! / 60000.0

    private fun getAccuracy(): Int = ((_typedKeyList.value.count{ it is Key.OkNoted } / _typedKeyList.value.count().toDouble()) * 100).toInt()

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