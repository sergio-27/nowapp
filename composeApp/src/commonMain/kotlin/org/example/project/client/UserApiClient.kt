package org.example.project.client

import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.project.models.User
import org.example.project.utils.AppConstants

object UserApiClient {

    /**
     * Get /users/{id} every pollIntervalMillis until the server responds 200 OK.
     * Emits true when success; completes after that.
     */
    suspend fun getUserById(id: String): User? {
        try {
            //TODO mcreate mapper for responses from DTO to Model
            print("https://interzonal-flurriedly-madisyn.ngrok-free.dev/users/$id")
            return apiClient.get("${AppConstants.requestedCredentialMetadataConfig.urlPrefix}/users/$id").body<User>()
        } catch (error: ResponseException) {
            print("Error on ping: ${error}")
            return null
        }

    }

    /**
     * Polls /api/single/{id} every pollIntervalMillis until the server responds 200 OK.
     * Emits true when success; completes after that.
     */
    //TODO map response to User model
    suspend fun createUser(body: User): User? {
        try {
            return apiClient.post("${AppConstants.requestedCredentialMetadataConfig.urlPrefix}/users") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }.body()
        } catch (error: Exception) {
            print("Error on ping: $error")
            return null
        }

    }
}
