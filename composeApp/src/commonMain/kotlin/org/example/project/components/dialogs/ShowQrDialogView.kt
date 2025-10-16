package org.example.project.components.dialogs

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import qrgenerator.QRCodeImage

@Composable
fun ShowQrDialogView(
    isDialogOpen: Boolean,
    onDismissRequest: () -> Unit,
    qrUrl: String?,
    responseText: String,
) {


    CustomDialog(isDialogOpen = isDialogOpen, onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (!qrUrl.isNullOrBlank()) {
                true -> Crossfade(targetState = responseText) { qrCodeUrl ->
                    QRCodeImage(
                        url = qrUrl,
                        modifier = Modifier.size(250.dp),
                        contentDescription = "QR Code",
                        onSuccess = {
                            println("QR Code generated successfully $it")
                        }
                    )
                }
                false -> Row(horizontalArrangement = Arrangement.Center) { CircularProgressIndicator() }
            }
        }
    }
}