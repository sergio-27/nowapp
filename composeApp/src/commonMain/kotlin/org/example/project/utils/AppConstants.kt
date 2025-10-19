package org.example.project.utils

import org.example.project.models.Config
import org.example.project.models.Credential

object AppConstants {
    private const val VERIFIER_BASE_URL = "https://interzonal-flurriedly-madisyn.ngrok-free.dev"
    private const val EUPID_CREDENTIAL_TYPE = "urn:eu.europa.ec.eudi:pid:1"

    val requestedCredentialMetadataConfig = Config(
        urlPrefix = VERIFIER_BASE_URL,
        credentials = listOf(
            Credential(
                credentialType = EUPID_CREDENTIAL_TYPE,
                representation = "SD_JWT",
                attributes = listOf(
                    "family_name",
                    "given_name",
                    "birth_date"
                )
            )
        )
    )

}