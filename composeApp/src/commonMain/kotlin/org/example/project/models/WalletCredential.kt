package org.example.project.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/** Holds a credential sent from the Wallet */
@Serializable
data class WalletCredential(
    val allFields: JsonObject? = null,
    val credentialType: String? = null,
)