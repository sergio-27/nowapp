package org.example.project.components.dialogs

import androidx.compose.runtime.Composable

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