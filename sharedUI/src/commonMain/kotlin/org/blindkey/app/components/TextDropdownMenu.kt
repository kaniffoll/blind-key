package org.blindkey.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.blindkey.app.res.Dimens

@Composable
fun TextDropdownMenu(initValue: String, vararg params: String, onItemSelect: (String) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    var expanded by remember { mutableStateOf(false) }
    var selectedValue by remember { mutableStateOf(initValue) }
    Box(modifier = Modifier) {
        Box(
            Modifier.border(
                Dimens.borderStroke,
                MaterialTheme.colors.secondary.copy(alpha = 0.5f),
                shape = RoundedCornerShape(Dimens.small)
            )
                .padding(vertical = Dimens.dropDownTextPadding, horizontal = Dimens.small)
                .hoverable(interactionSource, falsec),
            contentAlignment = Alignment.Center
        ) {
            Text(text = selectedValue, modifier = Modifier.clickable { expanded = !expanded })
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            params.forEach {
                if (it != selectedValue)
                    DropdownMenuItem(onClick = {
                        onItemSelect(it)
                        selectedValue = it
                        expanded = false
                    }) {
                        Text(text = it)
                    }
            }
        }
    }
}