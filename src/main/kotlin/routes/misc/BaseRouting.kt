package com.example.routes.misc

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRoutingBase() {
    routing {
        get("/") {
            call.respondText("Book Central")
        }
    }
}
