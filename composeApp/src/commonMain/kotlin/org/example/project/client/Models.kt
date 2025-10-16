package org.example.project.client

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class UserResponse(
    val authenticatedUser: AuthenticatedUser,

    )

@Serializable
data class AuthenticatedUser(
    val id: String,
    val timestamp: Long,
    val credentials: List<WalletCredential>,
)

/** Holds a credential sent from the Wallet */
@Serializable
data class WalletCredential(
    val allFields: JsonObject? = null,
    val credentialType: String? = null,
)