package org.example.project.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.example.project.components.buttons.PrimaryButton

@Composable
fun LoginOptionsView(
    isLoading: Boolean,
    onClickUseQrCode: () -> Unit,
    onClickLogin: () -> Unit
) {
    var userTextField by remember { mutableStateOf("") }
    var passwordTextField by remember { mutableStateOf("") }

    val onUsernameTextFieldChange: (String) -> Unit = { userTextField = it }
    val onPasswordTextFieldChange: (String) -> Unit = { passwordTextField = it }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = 6.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            TextField(
                value = userTextField,
                placeholder = { Text("User") },
                onValueChange = onUsernameTextFieldChange,
            )
            TextField(
                value = passwordTextField,
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text("Password") },
                onValueChange = onPasswordTextFieldChange,
            )
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            PrimaryButton(
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                buttonText = "Login",
                onClick = onClickLogin
            )
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            PrimaryButton(
                enabled = !isLoading,
                buttonText = "Use QR Code",
                onClick = onClickUseQrCode
            )
        }
    }
}