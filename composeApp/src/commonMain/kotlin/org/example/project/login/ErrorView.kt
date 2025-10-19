package org.example.project.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.components.buttons.PrimaryButton
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ErrorView(onClickBackToLogin: () -> Unit = {}) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Column(
            Modifier.fillMaxWidth().weight(1f).padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "ERROR MI PANA, TU NO ERES MINISTROOOO. DATE DE ALTA EN EL MINISTERIO PARA PODER CONTINUAR.",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            PrimaryButton(
                buttonText = "Back to Login",
                onClick = onClickBackToLogin
            )
        }
    }
}