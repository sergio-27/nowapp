package org.example.project.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.client.Config
import org.example.project.client.Credential
import org.example.project.components.buttons.PrimaryButton
import org.example.project.components.dialogs.ShowQrDialogView
import org.jetbrains.compose.ui.tooling.preview.Preview
import qrgenerator.qrkitpainter.rememberQrKitPainter


@Composable
@Preview
fun LoginScreen(
    transactionViewModel: TransactionViewModel = viewModel { TransactionViewModel() },
    onLoginSuccess: () -> Unit,
) {

    val qrUrl by transactionViewModel.qrUrl.collectAsState()
    val isSuccess by transactionViewModel.isSuccess.collectAsState()
    val isLoading by transactionViewModel.isLoading.collectAsState()

    var responseText by remember { mutableStateOf("") }

    val painter = rememberQrKitPainter(data = responseText)
    var isDialogOpen by remember { mutableStateOf(false) }


    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            onLoginSuccess()
            isDialogOpen = false
        }
    }

    Scaffold {
        Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                PrimaryButton(
                    enabled = !isLoading,
                    buttonText = "Usar código QR",
                ) {
                    isDialogOpen = true
                    transactionViewModel.start(
                        Config(
                            urlPrefix = "https://interzonal-flurriedly-madisyn.ngrok-free.dev",
                            credentials = listOf(
                                Credential(
                                    credentialType = "urn:eu.europa.ec.eudi:pid:1",
                                    representation = "SD_JWT",
                                    attributes = listOf(
                                        "family_name",
                                        "given_name",
                                        "birth_date"
                                    )
                                )
                            )
                        )
                    )
                }
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                PrimaryButton(
                    enabled = !isLoading,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    buttonText = "Activar Credencial",
                ) {

                }
            }

        }
    }

    if (isDialogOpen) {
        ShowQrDialogView(
            isDialogOpen = isDialogOpen,
            qrUrl = qrUrl,
            responseText = responseText,
            onDismissRequest = {
                transactionViewModel.cancel()
                isDialogOpen = false
            }
        )
    }

}