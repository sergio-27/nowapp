package org.example.project.client

import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.project.models.AuthenticatedUser
import org.example.project.models.AuthenticatedUserResponse
import org.example.project.models.Config
import org.example.project.models.Response
import org.example.project.utils.AppConstants

object AuthorizationApiClient {

    suspend fun createTransaction(body: Config): Response? {
        try {
            return apiClient.post("${AppConstants.requestedCredentialMetadataConfig.urlPrefix}/verifier/transaction/create") {
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
    fun pollForResult(id: String, pollIntervalMillis: Long = 2000L): Flow<AuthenticatedUser> =
        flow {
            while (true) {
                try {
                    val response: HttpResponse =
                        apiClient.get("${AppConstants.requestedCredentialMetadataConfig.urlPrefix}/verifier/api/single/$id")
                    if (response.status == HttpStatusCode.OK) {
                        emit(response.body<AuthenticatedUserResponse>().authenticatedUser)
                        break
                    }
                } catch (t: ResponseException) {
                    // server not ready or network error -> ignore and continue polling
                    print("Error on ping: ${t}")
                }
                delay(pollIntervalMillis)
            }
        }
}
