package com.ktorjetpackcompose.config

import android.util.Log
import com.ktorjetpackcompose.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object KtorClient {
    val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("KtorLogger", message) // Log to Logcat
                }
            }
            /**
             * How to show log for api request
            LogLevel.ALL: Logs everything, including headers, request body, response body, and metadata.
            LogLevel.BODY: Logs only the request and response bodies.
            LogLevel.HEADERS: Logs only the request and response headers.
            LogLevel.INFO: Logs request and response lines (method, URL, status code).
            LogLevel.NONE: Disables logging entirely.
            **/
            level = if (BuildConfig.DEBUG) LogLevel.BODY else LogLevel.NONE
        }
        defaultRequest {
            url("https://jsonplaceholder.typicode.com") // Set base URL
        }
    }
}