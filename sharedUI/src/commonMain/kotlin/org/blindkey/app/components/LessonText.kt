package org.blindkey.app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.key.utf16CodePoint
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import org.blindkey.app.model.Key
import org.blindkey.app.res.Dimens
import org.blindkey.app.screens.test.MainViewModel

@Composable
fun LessonText(
    text: String,
    typedKeyList: List<Key>,
    onCheckKey: (Char) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .focusable()
            .onFocusChanged { isFocused = it.isFocused }
            .focusRequester(focusRequester)
            .clickable { focusRequester.requestFocus() }
            .padding(Dimens.large)
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    event.utf16CodePoint.toChar().let { char ->
                       onCheckKey(char)
                    }
                }
                true
            }
            .blur(
                radius = if (isFocused) Dimens.zero else Dimens.small,
                edgeTreatment = BlurredEdgeTreatment.Unbounded,
            ),
        contentAlignment = Alignment.TopStart
    ) {

        Text(
            text,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.alpha(0.5f),
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            buildAnnotatedString {
                typedKeyList.forEachIndexed { index, it ->
                    val color = when (it) {
                        is Key.ErrNoted -> MaterialTheme.colorScheme.error
                        is Key.OkNoted -> MaterialTheme.colorScheme.primary
                    }

                    val isLast = index == typedKeyList.size - 1

                    val decoration = if (isLast) TextDecoration.Underline else TextDecoration.None

                    withStyle(
                        style = MaterialTheme.typography.bodyLarge.toSpanStyle()
                            .copy(color = color, textDecoration = decoration)
                    ) {
                        append(it.key.toString())
                    }
                }
            },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

