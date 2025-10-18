package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val id: String,
    val qrCodeUrl: String,
    val qrCodePng: String,
)