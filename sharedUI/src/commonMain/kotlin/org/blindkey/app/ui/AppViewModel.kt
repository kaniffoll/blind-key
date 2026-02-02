package org.blindkey.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.blindkey.app.model.Key
import org.blindkey.domain.usecase.GetRandomTextUseCase
import org.blindkey.domain.usecase.InitDatabaseUseCase

class AppViewModel(
    private val getRandomTextUseCase: GetRandomTextUseCase,
    private val initDatabaseUseCase: InitDatabaseUseCase,
) : ViewModel() {
    private var _currentText = MutableStateFlow("")
    val currentText = _currentText.asStateFlow()

    private var _currentIndex = MutableStateFlow(0)
    val currentIndex = _currentIndex.asStateFlow()

    private var _typedKeyList = MutableStateFlow(emptyList<Key>())
    val typedKeyList = _typedKeyList.asStateFlow()

    val currentKey: Char?
        get() = if (currentIndex.value == _currentText.value.count()) null
        else _currentText.value[_currentIndex.value]

    private var isErrForCurrentKey = false
    private var errCount = 0

    init {
        viewModelScope.launch(Dispatchers.Default) {
            initDatabaseUseCase()
            getNewText()
        }
    }

    fun updateLocalData() {
        viewModelScope.launch(Dispatchers.Default) {
            initDatabaseUseCase(true)
        }
    }

    fun getNewText() {
        viewModelScope.launch(Dispatchers.Default) {
            _currentText.value = getRandomTextUseCase().content
        }
    }

    fun checkKey(key: Char) {
        currentKey ?: return

        if (key == currentKey) {
            val newKey = if (isErrForCurrentKey) Key.ErrNoted(key) else Key.OkNoted(key)
            _typedKeyList.value = _typedKeyList.value.toMutableList().apply { add(newKey) }
            isErrForCurrentKey = false
            _currentIndex.value += 1
            return
        }
        isErrForCurrentKey = true
        errCount++
    }
}
