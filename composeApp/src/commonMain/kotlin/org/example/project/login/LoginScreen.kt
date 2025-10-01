package org.example.project.login

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.example.project.client.ApiClient
import org.example.project.client.Config
import org.example.project.client.Credential
import org.example.project.components.buttons.PrimaryButton
import org.example.project.components.dialogs.CustomDialog
import org.jetbrains.compose.ui.tooling.preview.Preview
import qrgenerator.QRCodeImage
import qrgenerator.qrkitpainter.rememberQrKitPainter


@Composable
@Preview
fun LoginScreen(
    navController: NavController,
    transactionViewModel: TransactionViewModel
) {
    val qrUrl by transactionViewModel.qrUrl.collectAsState()
    val isSuccess by transactionViewModel.isSuccess.collectAsState()
    val isLoading by transactionViewModel.isLoading.collectAsState()

    var responseText by remember { mutableStateOf("") }

    val painter = rememberQrKitPainter(data = responseText)
    var isDialogOpen by remember { mutableStateOf(false) }


    Scaffold(
        bottomBar = {
            Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.Center) {
                PrimaryButton(
                    enabled = !isLoading,
                    buttonText = "Usar código QR",
                ) {
                    isDialogOpen = true
                    transactionViewModel.start(
                        Config(
                            urlPrefix = "haip://",
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
        }
    ) {
        if (isSuccess) {
            // Navigate to Home Screen
            CoroutineScope(Dispatchers.IO).launch {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }
    }

    if (isDialogOpen) {
        CustomDialog(isDialogOpen = isDialogOpen, onDismissRequest = {
            isDialogOpen = false
            transactionViewModel.cancel()
        }) {
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
                            url = qrUrl ?: "",
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

}