package com.singlepointsolution.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.singlepointsolution.core.theme.MEDIUM_PADDING
import com.singlepointsolution.core.theme.SMALL_PADDING

@Composable
fun TaskContent(
    note: String,
    label: String,
    onNoteChange: (String) -> Unit,
    onAddButtonClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(all = MEDIUM_PADDING)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = note,
            onValueChange = { onNoteChange(it) },
            label = { Text(text = label) },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )
        Divider(
            modifier = Modifier.height(SMALL_PADDING),
            color = MaterialTheme.colors.background
        )
        Button(
            onClick = onAddButtonClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add TODO", fontSize = 18.sp)
        }
    }
}

@Composable
@Preview
private fun TaskContentPreview() {
    TaskContent(note = "", label = "", onNoteChange = {}, onAddButtonClick = {})
}