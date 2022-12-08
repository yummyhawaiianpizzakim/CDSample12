package com.example.cdsample12.UIitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CommonDialog(
    title: String?,
    state: MutableState<Boolean>,
    content: @Composable (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = {
            state.value = false
        },
        title = title?.let {
            {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = title)
                    Divider(modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        },
        text = content,
        dismissButton = {
            Button(onClick = { state.value = false }) {
                Text("Cancel")
            }
        },
        confirmButton = {
            Button(onClick = { state.value = false }) {
                Text("Ok")
            }
        }, modifier = Modifier.padding(vertical = 8.dp)
    )
}


@Composable
fun NumberpickerView(state: MutableState<Boolean>) {
    CommonDialog(title = "Select People", state = state) {
        NumberPicker()
    }
}


@Composable
fun NumberPicker() {
    var state by remember { mutableStateOf(0) }
    com.chargemap.compose.numberpicker.NumberPicker(
        value = state,
        range = 0..10,
        onValueChange = {
            state = it
        }
    )
}
