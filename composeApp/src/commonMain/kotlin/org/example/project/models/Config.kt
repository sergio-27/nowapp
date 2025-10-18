package org.example.project.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Config(
    /** Prefix for the URL displayed to start Wallet authentication, e.g. `haip://` */
    @SerialName("urlprefix")
    val urlPrefix: String,

    val credentials: List<Credential>
)