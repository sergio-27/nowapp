package org.example.project.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    buttonTextModifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonText: String,
    onClick: () -> Unit
) {

    Button(modifier = modifier, onClick = onClick, enabled = enabled) {
        Text(text = buttonText, modifier = buttonTextModifier)
    }
}