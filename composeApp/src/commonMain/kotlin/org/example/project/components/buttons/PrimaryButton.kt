package org.example.project.components.buttons

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    buttonTextModifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    buttonText: String,
    onClick: () -> Unit
) {

    Button(modifier = modifier.defaultMinSize(minWidth = 150.dp), onClick = onClick, enabled = enabled, colors = colors) {
        Text(text = buttonText, modifier = buttonTextModifier)
    }
}