package com.jacobselbo

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

/**
 * Extension function of ApplicationCall to handle client error responses
 *
 * @param status HTTP status code, like BadRequest
 * @param message Reason for the error
 */
suspend fun ApplicationCall.respondError(status: HttpStatusCode, message: String) {
    respond(status, mapOf(
        "message" to message,
        "status" to status.value
    ))
}