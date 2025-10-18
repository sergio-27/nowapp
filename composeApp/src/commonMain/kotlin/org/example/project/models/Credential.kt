package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class Credential(
    /** Set an SD-JWT type or ISO doctype of a credential known to this service */
    val credentialType: String,

    /** Set `SD_JWT` or `PLAIN_JWT` or `ISO_MDOC` */
    val representation: String,

    /** Optionally set which attributes should be requested from the Wallet */
    val attributes: List<String>? = null
)