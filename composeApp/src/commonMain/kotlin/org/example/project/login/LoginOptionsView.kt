package org.example.project.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.project.components.buttons.PrimaryButton

@Composable
fun LoginOptionsView(
    isLoading: Boolean,
    onClickUseQrCode: () -> Unit,
    onClickActivateCredential: () -> Unit
) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            PrimaryButton(
                enabled = !isLoading,
                buttonText = "Usar código QR",
                onClick = onClickUseQrCode
            )
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            PrimaryButton(
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                buttonText = "Activar Credencial",
                onClick = onClickActivateCredential
            )
        }
    }
}