package org.example.project.components.dialogs

import androidx.compose.runtime.Composable
import org.example.project.components.dialogs.compose.ComposeDialog

@Composable
actual fun CustomDialog(
    isDialogOpen: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    ComposeDialog(
        isDialogOpen = isDialogOpen,
        onDismissRequest = onDismissRequest,
        content = content
    )
}