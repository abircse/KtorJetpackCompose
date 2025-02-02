package com.ktorjetpackcompose.utils

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class GenericApiHelper(val client: HttpClient) {

    /** Generic GET request **/
    suspend inline fun <reified T> getRequest(url: String, headers: Map<String, String> = emptyMap(), params: Map<String, String> = emptyMap()): T {
        return client.get(url) {
            // Add headers if provided
            headers.forEach { (key, value) -> header(key, value) }

            // Add query parameters if provided
            url {
                params.forEach { (key, value) -> parameters.append(key, value) }
            }
        }.body()
    }

    /** Generic POST request **/
    suspend inline fun <reified T> postRequest(url: String, body: Any, headers: Map<String, String> = emptyMap(), params: Map<String, String> = emptyMap()): T {
        return client.post(url) {
            // Set content type to application/json
            contentType(ContentType.Application.Json)

            // Add body
            setBody(body)

            // Add headers
            headers.forEach { (key, value) -> header(key, value) }

            // Add query parameters if provided
            url {
                params.forEach { (key, value) -> parameters.append(key, value) }
            }
        }.body()
    }

    /** Generic PUT request **/
    suspend inline fun <reified T> putRequest(url: String, body: Any, headers: Map<String, String> = emptyMap()): T {
        return client.put(url) {
            // Set content type to application/json
            contentType(ContentType.Application.Json)

            // Add body
            setBody(body)

            // Add headers
            headers.forEach { (key, value) -> header(key, value) }
        }.body()
    }

    /** Generic DELETE request with Flow **/
    suspend inline fun <reified T> deleteRequest(
        url: String,
        headers: Map<String, String> = emptyMap()
    ): Flow<Resource<T>> = flow {
        emit(Resource.Loading())
        try {
            val response: T = client.delete(url) {

                /** Add headers **/
                headers.forEach { (key, value) -> header(key, value) }

            }.body()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }.flowOn(Dispatchers.IO)
}
