package org.example.project

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberDialogState
import androidx.compose.ui.window.rememberWindowState
import kotlinx.serialization.Serializable

fun main() = application {
    // Start the server when the app launches
    // Start server on app launch
    val windowState = rememberWindowState(
        position = WindowPosition(Alignment.Center)
    )
    Window(
        state = windowState,
        onCloseRequest = ::exitApplication,
        title = "DesktopKotlinProject",
    ) {
        App()
    }
}

// Example data model (can be in commonMain for multiplatform sharing)
@Serializable
data class ResponseData(val message: String, val timestamp: Long = System.currentTimeMillis())