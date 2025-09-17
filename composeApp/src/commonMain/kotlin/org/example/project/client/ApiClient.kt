package org.example.project.client
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object ApiClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun ping(body: Config): Response? {
        try {
            return client.post("http://localhost:8080/verifier/transaction/create") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }.body()
        } catch (error: Exception) {
            print("Error on ping: $error")
            return null
        }

    }
}


@Serializable
data class Config(
    /** Prefix for the URL displayed to start Wallet authentication, e.g. `haip://` */
    @SerialName("urlprefix")
    val urlPrefix: String,

    val credentials: List<Credential>
)

@Serializable
data class Credential(
    /** Set an SD-JWT type or ISO doctype of a credential known to this service */
    val credentialType: String,

    /** Set `SD_JWT` or `PLAIN_JWT` or `ISO_MDOC` */
    val representation: String,

    /** Optionally set which attributes should be requested from the Wallet */
    val attributes: List<String>? = null
)

@Serializable
data class Response(
    val id: String,
    val qrCodeUrl: String,
    val qrCodePng: String,
)