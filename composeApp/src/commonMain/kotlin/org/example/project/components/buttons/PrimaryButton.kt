package org.example.project.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    buttonTextModifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    buttonText: String,
    onClick: () -> Unit
) {

    Button(modifier = modifier, onClick = onClick, enabled = enabled, colors = colors) {
        Text(text = buttonText, modifier = buttonTextModifier)
    }
}