package org.example.project.components.dialogs

import androidx.compose.runtime.Composable

@Composable
expect fun CustomDialog(isDialogOpen: Boolean, onDismissRequest: () -> Unit, content: @Composable () -> Unit)