package org.example.project.login.activate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.components.buttons.PrimaryButton
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ActivateCredentialScreen(
    onClickBack: () -> Unit = {}
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("VAMO A REGISTRARNOS MINISTROOOO")
        PrimaryButton(
            buttonText = "Tirar pa tras",
            onClick = onClickBack
        )
    }
}