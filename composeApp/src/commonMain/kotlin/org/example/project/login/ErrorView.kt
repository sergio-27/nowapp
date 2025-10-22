package org.example.project.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.components.buttons.PrimaryButton
import org.example.project.models.AuthenticatedUser
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ErrorView(
    authenticatedUserCredential: AuthenticatedUser?,
    onClickRegisterUser: (authenticatedUserCredential: AuthenticatedUser) -> Unit = {},
    onClickBackToLogin: () -> Unit = {}
) {

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Column(
            Modifier.fillMaxWidth().weight(1f).padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(
                8.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                when (authenticatedUserCredential != null) {
                    true -> "ERROR MI PANA, TU NO ERES MINISTROOOO. DATE DE ALTA EN EL MINISTERIO QUE TE CORRESPONDA PARA PODER CONTINUAR."
                    false -> "ERROR MI PANA, TU NO LLEGA NI A DIPUTAO. OBTEN UNA CREDENCIAL VÁLIDA PARA PODER CONTINUAR."
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            when (authenticatedUserCredential != null) {
                true -> {
                    PrimaryButton(
                        buttonText = "Registrarme",
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                        onClick = { onClickRegisterUser(authenticatedUserCredential) }
                    )
                    PrimaryButton(
                        buttonText = "Back to Login",
                        onClick = onClickBackToLogin
                    )
                }

                false -> {
                    PrimaryButton(
                        buttonText = "Back to Login",
                        onClick = onClickBackToLogin
                    )
                }
            }
        }
    }
}