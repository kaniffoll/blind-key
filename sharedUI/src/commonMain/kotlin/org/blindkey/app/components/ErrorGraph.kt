package org.blindkey.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import org.blindkey.app.res.Dimens

@Composable
fun ErrorGraph(list: List<Int>) {
    val smallAndMiddle = categorize(list)
    val small = smallAndMiddle.first
    val middle = smallAndMiddle.second

    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(Dimens.medium),
        horizontalArrangement = Arrangement.spacedBy(Dimens.small2),
        verticalAlignment = Alignment.Bottom
    ) {
        list.forEach { num ->
            item {
                when {
                    num == 0 -> ErrorColumn(Categorization.Zero())
                    num <= small -> ErrorColumn(Categorization.Small(num))
                    num <= middle -> ErrorColumn(Categorization.Middle(num))
                    else -> ErrorColumn(Categorization.Large(num))
                }
            }
        }
    }
}

@Composable
private fun ErrorColumn(categorization: Categorization) {
    val color = MaterialTheme.colorScheme.primary
    Box(
        modifier = Modifier
            .height(categorization.dimen)
            .width(Dimens.medium)
            .background(
                color = color, shape = RoundedCornerShape(
                    topStart = Dimens.small,
                    topEnd = Dimens.small,
                    bottomStart = Dimens.zero,
                    bottomEnd = Dimens.zero,
                )
            )
            .drawBehind {
                drawLine(
                    color = color,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                )
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = categorization.value.toString(),
            modifier = Modifier.padding(horizontal = Dimens.small),
            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onPrimary)
        )
    }
}

private fun categorize(list: List<Int>): Pair<Double, Double> {
    val numbersNotZero = list.filter { it != 0 }
    val min = numbersNotZero.minOrNull() ?: 0
    val max = numbersNotZero.maxOrNull() ?: 0
    val step = (max - min) / 3.0
    val small = min + step
    val middle = min + 2 * step
    return Pair(small, middle)
}

sealed class Categorization(open val value: Int, open val dimen: Dp) {
    data class Zero(override val value: Int = 0, override val dimen: Dp = Dimens.zero) : Categorization(value, dimen)
    data class Small(override val value: Int, override val dimen: Dp = Dimens.smallErrColumn) :
        Categorization(value, dimen)

    data class Middle(override val value: Int, override val dimen: Dp = Dimens.mediumErrColumn) :
        Categorization(value, dimen)

    data class Large(override val value: Int, override val dimen: Dp = Dimens.largeErrColumn) :
        Categorization(value, dimen)
}