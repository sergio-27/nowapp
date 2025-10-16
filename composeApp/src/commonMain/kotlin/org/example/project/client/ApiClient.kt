package org.example.project.client
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

object ApiClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun createTransaction(body: Config): Response? {
        try {
            return client.post("https://interzonal-flurriedly-madisyn.ngrok-free.dev/verifier/transaction/create") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }.body()
        } catch (error: Exception) {
            print("Error on ping: $error")
            return null
        }

    }

    /**
     * Polls /api/single/{id} every pollIntervalMillis until the server responds 200 OK.
     * Emits true when success; completes after that.
     */
    fun pollForResult(id: String, pollIntervalMillis: Long = 2000L): Flow<AuthenticatedUser> = flow {
        while (true) {
            try {
                val response: HttpResponse = client.get("https://interzonal-flurriedly-madisyn.ngrok-free.dev/verifier/api/single/$id")
                if (response.status == HttpStatusCode.OK) {

                    emit(response.body<UserResponse>().authenticatedUser)
                    break
                }
            } catch (t: Throwable) {
                // server not ready or network error -> ignore and continue polling
                print("Error on ping: ${t.message}")
            }
            delay(pollIntervalMillis)
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