package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticatedUser(
    val id: String,
    val timestamp: Long,
    val credentials: List<WalletCredential>,
)