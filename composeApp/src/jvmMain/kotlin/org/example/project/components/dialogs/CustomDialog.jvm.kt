package org.example.project.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.rememberDialogState

@Composable
actual fun CustomDialog(
    isDialogOpen: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    val dialogWindowState = rememberDialogState(
        size = DpSize(width = 500.dp, height = 400.dp)
    )
    DialogWindow(
        state = dialogWindowState,
        visible = isDialogOpen,
        resizable = false,
        onCloseRequest = onDismissRequest,
    ) {
        content()
    }
}