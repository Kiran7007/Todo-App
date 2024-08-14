package com.singlepointsolution.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.singlepointsolution.core.theme.MediumGray

@Composable
fun EmptyContent(
    errorMessage: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMessage,
            color = MediumGray,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
    }
}

@Composable
@Preview
private fun EmptyContentPreview() {
    EmptyContent("")
}