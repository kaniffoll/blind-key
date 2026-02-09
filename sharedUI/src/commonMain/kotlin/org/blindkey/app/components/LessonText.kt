package org.blindkey.app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import org.blindkey.app.model.Key
import org.blindkey.app.res.Dimens

@Composable
fun LessonText(
    text: String,
    typedKeyList: List<Key>,
    isFocused: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(Dimens.large)
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

