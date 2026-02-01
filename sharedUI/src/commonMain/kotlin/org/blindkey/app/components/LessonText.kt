package org.blindkey.app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import org.blindkey.app.model.Key

@Composable
fun LessonText(text: String, typedKeyList: List<Key>, onClick: () -> Unit) {
    Box(
        modifier = Modifier.clickable(onClick = onClick),
        contentAlignment = Alignment.TopStart
    ) {
        Text(text, color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.alpha(0.5f))

        Text(buildAnnotatedString {
            typedKeyList.forEach {
                when (it) {
                    is Key.ErrNoted -> withStyle(SpanStyle(color = MaterialTheme.colorScheme.error)) {
                        append(it.key.toString())
                    }

                    is Key.OkNoted -> withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(it.key.toString())
                    }
                }
            }
        })
    }
}
