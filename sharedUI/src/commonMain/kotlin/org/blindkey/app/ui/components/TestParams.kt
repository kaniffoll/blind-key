package org.blindkey.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import blind_key.sharedui.generated.resources.Res
import blind_key.sharedui.generated.resources.punctuation_label
import blind_key.sharedui.generated.resources.language_label
import blind_key.sharedui.generated.resources.length_label
import org.blindkey.app.res.Dimens
import org.blindkey.domain.model.Language
import org.blindkey.domain.model.Length
import org.blindkey.domain.model.TestParam
import org.jetbrains.compose.resources.stringResource

@Composable
fun TestParams(
    testParam: TestParam,
    onChange: (TestParam.Companion.Param) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        LabelAndMenu(
            initValue = testParam.hasPunctuationAsString(),
            label = stringResource(Res.string.punctuation_label),
            TestParam.HAS_PUNCTUATION_YES,
            TestParam.HAS_PUNCTUATION_NO,
            TestParam.ALL_STR
        ) {
            onChange(TestParam.Companion.Param.HasPunctuation(it))
        }

        LabelAndMenu(
            initValue = testParam.languageAsString(),
            label = stringResource(Res.string.language_label),
            Language.ENGLISH.value,
            Language.RUSSIAN.value,
            TestParam.ALL_STR
        ) {
            onChange(TestParam.Companion.Param.Language(it))
        }

        LabelAndMenu(
            initValue = testParam.lengthAsString(),
            label = stringResource(Res.string.length_label),
            Length.SMALL_NAME,
            Length.MEDIUM_NAME,
            Length.LARGE_NAME,
            TestParam.ALL_STR
        ) {
            onChange(TestParam.Companion.Param.Length(it))
        }
    }
}

@Composable
private fun LabelAndMenu(
    initValue: String,
    label: String,
    vararg params: String,
    onItemSelected: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.small2)
    ) {
        Text(label)
        TextDropdownMenu(initValue, *params) {
            onItemSelected(it)
        }
    }
}


