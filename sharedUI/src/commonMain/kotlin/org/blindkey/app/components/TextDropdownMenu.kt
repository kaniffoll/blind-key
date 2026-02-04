package org.blindkey.app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun TextDropdownMenu(initValue: String, vararg params: String, onItemSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedValue by remember { mutableStateOf(initValue) }
    Box(modifier = Modifier) {
        Text(text = selectedValue, modifier = Modifier.clickable { expanded = !expanded })
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