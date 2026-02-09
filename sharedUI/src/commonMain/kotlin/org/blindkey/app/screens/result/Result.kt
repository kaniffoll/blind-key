package org.blindkey.app.screens.result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.blindkey.app.screens.test.MainViewModel

@Composable
fun Result(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    val textParam = viewModel.getTestResult()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(textParam.totalTime.toString() + textParam.errorList.toString())
    }
}