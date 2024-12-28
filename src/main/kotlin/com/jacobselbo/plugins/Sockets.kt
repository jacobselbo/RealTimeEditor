package com.jacobselbo.plugins

import com.jacobselbo.respondError
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlin.time.Duration.Companion.seconds

/**
 * Configures the WebSocket to handle commands on the pages
 */
fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = 15.seconds
        timeout = 15.seconds
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        webSocket("/api/websocket/{PageID}") {
            val pageID = call.parameters["PageID"]?.toInt() ?:
                return@webSocket call.respondError(HttpStatusCode.BadRequest,
                    "Missing or malformed pageID")

            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val text = frame.readText()
                    outgoing.send(Frame.Text("YOU SAID: $text"))
                }
            }
        }
    }
}
